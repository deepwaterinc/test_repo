package com.education.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;

/**
 * @author Дарья Лукьянова
 * Класс для отправки запросов в edo-service
 */
@Component
@RequiredArgsConstructor
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
     * Базовый URL для API edo-rest
     */
    private final String BASIC_URL = "api/service/file";


    /**
     * Метод, конвертирующий файл в формат pdf
     *
     * @param multipartFile файл, который мы конвертируем
     */
    public void convertFile(MultipartFile multipartFile) {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", multipartFile.getResource());
        map.add("name", multipartFile.getOriginalFilename());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        restTemplate.exchange(getDefaultUriComponentBuilder(BASIC_URL)
                .build()
                .toUri(), HttpMethod.POST, requestEntity, String.class);

    }


    /**
     * Метод, возвращающий случайный экземпляр сервиса edo-repository
     *
     * @return Случайный экземпляр сервиса edo-service
     */
    private InstanceInfo getRandomInstance() {
        var instances = eurekaClient
                .getApplication("edo-service")
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
