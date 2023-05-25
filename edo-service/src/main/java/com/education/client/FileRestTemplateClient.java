package com.education.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

/**
 * Класс для отправки запросов в edo-file-storage
 */
@Component
@RequiredArgsConstructor
@Log
public class FileRestTemplateClient {

    /**
     * Объект класса EurekaClient
     */
    private final EurekaClient eurekaClient;
    /**
     * Объект класса RestTemplate
     */
    private final RestTemplate restTemplate;
    /**
     * Базовый URL для API edo-file-storage
     */
    private final String BASIC_URL = "/api/file-storage";
    /**
     * Имя модуля в который отправляются запросы
     */
    private static final String SERVICE_NAME = "edo-file-storage";

    /**
     * Метод, сохраняющий file в файловое хранилище
     *
     * @param file сохраняемый объект
     * @return объект каласса UUID, соответствующий сохраненному объекту
     */
    public UUID saveFile(byte[] file) {
       return restTemplate.postForObject(getDefaultUriComponentBuilder(BASIC_URL + "/upload")
                        .build()
                        .toUri()
                , file
                , UUID.class);
    }

    /**
     * Метод, возвращающий файл из файлового хранилища
     *
     * @param uuid имя файла, который мы хотим получить.
     */
    public byte[] getFile(UUID uuid) {
        try {
            var entity = RequestEntity.get(getDefaultUriComponentBuilder(BASIC_URL + "/download/{uuid}")
                    .buildAndExpand(uuid.toString())
                    .toUri()).build();
            return restTemplate.exchange(entity, byte[].class).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    /**
     * Метод, возвращающий случайный экземпляр сервиса
     *
     * @return Случайный экземпляр сервиса
     */
    private InstanceInfo getRandomInstance() {
        var instances = eurekaClient
                .getApplication(SERVICE_NAME)
                .getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * Метод, возвращающий общий для всех методов класса объект UriComponentsBuilder
     *
     * @param path адрес api
     * @return объект UriComponentsBuilder
     */
    private UriComponentsBuilder getDefaultUriComponentBuilder(String path) {
        InstanceInfo instanceInfo = getRandomInstance();
        return UriComponentsBuilder
                .fromPath(path)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort());
    }
}
