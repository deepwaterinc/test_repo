package com.education.client;

import com.education.model.dto.NomenclatureDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Представляет реализацию операций для связи с модулем edo-repository
 * для номенклатуры
 *
 * @author Иван Кузнецов
 * @version 1.0
 * @since 1.0
 */

@Component
@RequiredArgsConstructor
public class NomenclatureRestTemplateClient {

    /**
     * Клиент для отправки и получения запросов
     */
    private final RestTemplate restTemplate;

    /**
     * Клиент для получения instance
     */
    private final EurekaClient eurekaClient;

    /**
     * путь рест котроллера номенклатуры
     */
    static final String BASE_URL = "api/repository/nomenclature";

    /**
     * Имя модуля в который отправляются запросы
     */
    static final String SERVICE_NAME = "edo-repository";

    /**
     * Имя протокола для отправки запроса
     */
    static final String SCHEMA_NAME = "http";

    /**
     * Отправяляет запрос на сохранение номенклатуры
     *
     * @param nomenclature NomenclatureDto
     * @return NomenclatureDto
     */
    public NomenclatureDto save(NomenclatureDto nomenclature) {
        nomenclature.setCreationDate(ZonedDateTime.now());
        var request = new RequestEntity(nomenclature, HttpMethod.POST,
                buildUri(getInstance(), "/"));

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save nomenclature");
        }

        return response.getBody();
    }

    /**
     * Отправляет запрос для предоставления NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    public NomenclatureDto findById(Long id) {
        var request = new RequestEntity(null, HttpMethod.GET,
                buildUri(getInstance(), "/{id}", id));

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclature");
        }
        return response.getBody();
    }

    /**
     * Отправляет запрос для предоставления списка NomenclatureDto номенклатур по id
     *
     * @param ids List of id
     * @return List of NomenclatureDto
     */
    public List<NomenclatureDto> findAllById(List<Long> ids) {
        var request = new RequestEntity(ids, HttpMethod.POST,
                buildUri(getInstance(), "/findAll"));

        var response = restTemplate.exchange(request, new ParameterizedTypeReference<List<NomenclatureDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclatures");
        }
        return response.getBody();
    }

    /**
     * Отправляет запрос для предоставления не заархивированной NomenclatureDto номенклатуры по id
     *
     * @param id Long
     * @return NomenclatureDto
     */
    public NomenclatureDto findByIdNotArchived(Long id) {
        var request = new RequestEntity(null, HttpMethod.GET,
                buildUri(getInstance(), "/notArchived/{id}", id));

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclature");
        }
        return response.getBody();
    }

    /**
     * Отправляет запрос для предоставления списка не заархивированных NomenclatureDto номенклатур по id
     *
     * @param ids List of id
     * @return List of NomenclatureDto
     */
    public List<NomenclatureDto> findAllByIdNotArchived(List<Long> ids) {
        var request = new RequestEntity(ids, HttpMethod.POST,
                buildUri(getInstance(), "/notArchived"));

        var response = restTemplate.exchange(request, new ParameterizedTypeReference<List<NomenclatureDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't get nomenclatures");
        }
        return response.getBody();
    }

    /**
     * Отправляет запрос для перевода номенклатуры в архив
     *
     * @param id Long
     */
    public void moveToArchive(Long id) {
        var request = new RequestEntity(null, HttpMethod.PATCH,
                buildUri(getInstance(), "/archived/{id}", id));

        var response = restTemplate.exchange(request, NomenclatureDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't move to archive");
        }
    }

    /**
     * get random instance
     *
     * @return InstanceInfo
     */
    private InstanceInfo getInstance() {
        Random random = new Random();
        Application app = eurekaClient.getApplication(SERVICE_NAME);
        List<InstanceInfo> instances = app.getInstances();
        return instances.get(random.nextInt(instances.size()));
    }

    /**
     * Build URI
     *
     * @param instance InstanceInfo
     * @param path     This is the request path
     * @return URI
     */
    private URI buildUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEMA_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }

    /**
     * Build uri with id
     *
     * @param instance InstanceInfo
     * @param path     This is the request path
     * @param id       Long
     * @return URI
     */
    private URI buildUri(InstanceInfo instance, String path, Long id) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(SCHEMA_NAME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .buildAndExpand(id)
                .toUri();
    }

    private String getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .build().toString();
    }

    public List<NomenclatureDto> findByIndex(String index) {
        InstanceInfo instanceInfo = getInstance();
        String path = "/search/?index=" + index;
        var uri = getURIByInstance(instanceInfo, path);
        NomenclatureDto[] response = restTemplate.getForObject(uri, NomenclatureDto[].class);
        return Arrays.asList(response);
    }

}
