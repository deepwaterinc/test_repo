package com.education.service.deadline.impl;

import com.education.model.dto.DeadlineDto;
import com.education.service.deadline.DeadlineService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class DeadlineServiceImpl implements DeadlineService {

    private RestTemplate rest;
    private EurekaClient client;
    private final String BASE_URL = "/api/repository/deadline";
    private final String HTTP = "http";

    /**
     * Сохранение нового дедлайна
     */
    @Override
    public DeadlineDto save(DeadlineDto deadlineDto) {
        var request = new RequestEntity(deadlineDto, HttpMethod.POST, getUri("/"));

        var response = rest.exchange(request, DeadlineDto.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save the deadline");
        }
        return response.getBody();
    }

    /**
     * Обновление дедлайна, установка комментария
     */
    @Override
    public DeadlineDto update(DeadlineDto deadlineDto) {
        var request = new RequestEntity(deadlineDto, HttpMethod.PUT, getUri("/"));

        var response = rest.exchange(request, DeadlineDto.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't update the deadline");
        }
        return response.getBody();
    }

    /**
     * Удаление дедлайна по id
     */

    @Override
    public void delete(Long id) {
        var uri = getUri("/" + id);
        rest.delete(uri);
    }

    /**
     * Поиск дедлайна по id
     */

    @Override
    public DeadlineDto findById(Long id) {
        var uri = getUri("/" + id);
        try {
            return rest.getForObject(uri, DeadlineDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Поиск дедлайнов по списку id
     */

    @Override
    public List<DeadlineDto> findAllById(List<Long> ids) {
        InstanceInfo instance = getInstance();

        var request = new RequestEntity(null, HttpMethod.GET, getUri(instance, "", ids));

        var response = rest.exchange(request, new ParameterizedTypeReference<List<DeadlineDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Не удалось найти дедлайны");
        }
        return response.getBody();
    }


    /**
     * Метод для получения URI.
     */
    private URI getUri(String path) {
        InstanceInfo instance = getInstance();
        return UriComponentsBuilder
                .fromPath(BASE_URL + path)
                .scheme(HTTP)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(path)
                .toUri();
    }

    /**
     * Метод для получения URI.
     */
    private URI getUri(InstanceInfo instance, String path, List<Long> ids) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(HTTP)
                .host(instance.getHostName())
                .port(instance.getPort())
                .queryParam("ids", ids)
                .build()
                .toUri();
    }

    /**
     * Метод для получения Instance
     */

    public InstanceInfo getInstance() {
        String SERVICE_NAME = "edo-repository";
        List<InstanceInfo> instances
                = client.getApplication(SERVICE_NAME).getInstances();

        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getPort());

        return instance;
    }

}
