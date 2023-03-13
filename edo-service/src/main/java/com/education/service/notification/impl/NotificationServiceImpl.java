package com.education.service.notification.impl;

import com.education.model.dto.NotificationDto;
import com.education.service.notification.NotificationService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Хафизов Ильмир
 * <p>
 * Представляет реализацию операций над оповещением пользователя
 */
@Service
@AllArgsConstructor
@Log
public class NotificationServiceImpl implements NotificationService {

    /**
     * Клиент для отправки и получения запросов
     */
    private RestTemplate template;

    /**
     * Клиент для получения instance
     */
    private EurekaClient eurekaClient;

    /**
     * путь до рест контроллера репозитория
     */
    private static final String BASE_URL = "/api/repository/notification";

    /**
     * Название микросервиса, к которому мы пытаемся получить доступ
     */
    private final String serviceName = "edo-repository";

    /**
     * Сохранение оповещений в БД
     *
     * @param notificationDto
     */
    @Override
    public void save(NotificationDto notificationDto) {
        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);
        template.postForObject(uri, notificationDto, NotificationDto.class);
    }

    /**
     * Сохранение оповещений в БД
     * @param notificationSet
     */
    @Override
    public void saveAll(List<NotificationDto> notificationSet) {
        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);
        template.postForObject(uri, notificationSet, NotificationDto.class);
    }

    /**
     * Удаление оповещений в БД по id
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        URI uri = generateUri(this.getInstance(), id);
        template.delete(uri);
    }

    /**
     * Предоставляет NotificationDto оповещений из БД по id
     *
     * @param id
     * @return
     */
    @Override
    public NotificationDto findById(Long id) {
        URI uri = generateUri(this.getInstance(), id);
        return template.getForObject(uri, NotificationDto.class);
    }

    /**
     * Предоставляет список NotificationDto оповещений из БД по id
     *
     * @param id
     * @return
     */
    @Override
    public List<NotificationDto> findAllById(List<Long> id) {
        String lastPathComponent = "/findAll";
        URI uri = generateUri(this.getInstance(), lastPathComponent);
        var request = new RequestEntity<>(id, HttpMethod.POST, uri);
        var myBean = new ParameterizedTypeReference<List<NotificationDto>>() {
        };
        var response = template.exchange(request, myBean);
        return response.getBody();
    }

    /**
     * generateUri
     *
     * @param instance
     * @param id
     * @return
     */
    private URI generateUri(InstanceInfo instance, Long id) {
        String lastPartComponent = "/{id}";
        return UriComponentsBuilder.fromPath(BASE_URL + lastPartComponent)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
    }

    /**
     * generateUri
     *
     * @param instance
     * @param path
     * @return
     */
    private URI generateUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = eurekaClient.getApplication(serviceName).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }
}
