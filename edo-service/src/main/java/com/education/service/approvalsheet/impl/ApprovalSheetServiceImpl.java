package com.education.service.approvalsheet.impl;

import com.education.model.dto.ApprovalSheetDto;
import com.education.service.approvalsheet.ApprovalSheetService;
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
public class ApprovalSheetServiceImpl implements ApprovalSheetService {

    private RestTemplate rest;
    private EurekaClient client;
    private final String BASE_URL = "/api/repository/approval";
    private final String HTTP = "http";


    /**
     * Сохранение нового листа согласования
     */

    @Override
    public ApprovalSheetDto save(ApprovalSheetDto approvalSheet) {
        var request = new RequestEntity(approvalSheet, HttpMethod.POST, getUri(""));

        var response = rest.exchange(request, ApprovalSheetDto.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save the approval sheet");
        }
        return response.getBody();
    }

    /**
     * Архивация листа согласования по id
     */

    @Override
    public void moveToArchive(Long id) {
        var uri = getUri("/archive/"+ id);
        rest.put(uri, null);
    }

    /**
     * Поиск листа согласования по id
     */

    @Override
    public ApprovalSheetDto findById(Long id) {
        var uri = getUri("/"+id);
        try {
            return rest.getForObject(uri, ApprovalSheetDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Поиск листов согласования по списку id
     */

    @Override
    public List<ApprovalSheetDto> findAllById(List<Long> ids) {
        InstanceInfo instance = getInstance();

        var request = new RequestEntity(null, HttpMethod.GET, getUri(instance, "", ids));

        var response = rest.exchange(request, new ParameterizedTypeReference<List<ApprovalSheetDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Не удалось найти листы согласования");
        }
        return response.getBody();
    }

    /**
     * Поиск неархивированного листа согласования по id
     */

    @Override
    public ApprovalSheetDto findByIdNotArchived(Long id) {
        var uri = getUri("/notArchived/" + id);
        try {
            return rest.getForObject(uri, ApprovalSheetDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Поиск неархивированных листов согласования по списку id
     */

    @Override
    public List<ApprovalSheetDto> findAllByIdNotArchived(List <Long> ids) {
        InstanceInfo instance = getInstance();

        var request = new RequestEntity(null, HttpMethod.GET, getUri(instance, "/notArchived", ids));

        var response = rest.exchange(request, new ParameterizedTypeReference<List<ApprovalSheetDto>>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Не удалось найти листы согласования");
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
    private URI getUri(InstanceInfo instance, String path, List <Long> ids) {
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
