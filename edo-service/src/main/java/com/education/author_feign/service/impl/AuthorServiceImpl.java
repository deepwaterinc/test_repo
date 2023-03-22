package com.education.author_feign.service.impl;

import com.education.author_feign.service.AuthorService;
import com.education.model.dto.AuthorDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Имплементация интерфейса методов для работы в БД с сущностью Автора
 * Сервис
 * Модуль edo-service
 */
@Service
@AllArgsConstructor
@Log4j2
public class AuthorServiceImpl implements AuthorService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "/api/repository/author";
    private final String HTTP = "http";
    private final String SERVICE_NAME = "edo-repository";
    private EurekaClient client;

    /**
     * Сохранение сущности
     */
    @Override
    public ResponseEntity<AuthorDto> save(AuthorDto author) {
        var uri = getUri("");
        return restTemplate.postForEntity(uri, author, AuthorDto.class);

    }

    /**
     * Удаление сущности
     */
    @Override
    public ResponseEntity<String> delete(Long id) {
        var uri = getUri("/" + id.toString());
        restTemplate.delete(uri);
        return ResponseEntity.ok("DELETED");
    }

    /**
     * Поиск сущности по id
     */
    @Override
    public AuthorDto findById(Long id) {
        var uri = getUri("/" + id.toString());
        return restTemplate.getForEntity(uri, AuthorDto.class).getBody();
    }

    /**
     * Поиск сущностей по значениям их id
     */
    @Override
    public List<AuthorDto> findAllById(List<String> ids) {
        var uri = getUri("/findAll");
        return restTemplate.exchange(new RequestEntity<>(ids, HttpMethod.POST,uri), new ParameterizedTypeReference<List<AuthorDto>>() {
        }).getBody();
    }

    /**
     * Метод для получения URI.
     * @param path
     * @return URI
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
     * Метод для получения Instance
     * @return InstanceInfo
     */
    public InstanceInfo getInstance() {
        List<InstanceInfo> instances
                = client.getApplication(SERVICE_NAME).getInstances();

        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getPort());

        return instance;
    }
}
