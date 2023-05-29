package com.education.service.region.impl;

import com.education.model.dto.RegionDto;
import com.education.service.region.RegionService;
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
public class RegionServiceImpl implements RegionService {

    private RestTemplate rest;
    private EurekaClient client;
    private final String BASE_URL = "/api/repository/region";
    private final String HTTP = "http";

    /**
     * Метод для сохранения объекта region в БД.
     *
     * @param region
     */
    @Override
    public RegionDto save(RegionDto region) {
        var request = new RequestEntity(region, HttpMethod.POST, getUri(""));

        var response = rest.exchange(request, RegionDto.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Can't save region");
        }
        return response.getBody();
    }


    /**
     * Метод для поиска обращения Region по id.
     *
     * @param id
     * @return RegionDto
     */
    @Override
    public RegionDto findById(Long id) {
        var uri = getUri(Long.toString(id));
        try {
            return rest.getForObject(uri, RegionDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод поиска списка обращений Region по списку id
     *
     * @return List<RegionDto>
     */
    @Override
    public List<RegionDto> findAll() {
        return getRegionDtos("all");
    }


    /**
     * Метод получения списка регионов
     *
     * @param path
     * @return List<RegionDto>
     */
    @Override
    public List<RegionDto> getRegionDtos(String path) {
        var uri = getUri(path);
        var request = new RequestEntity<>(HttpMethod.GET, uri);
        try {
            var response
                    = rest.exchange(request, new ParameterizedTypeReference<List<RegionDto>>() {
            });
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Метод для получения URI.
     *
     * @param path
     * @return URI
     */
    @Override
    public URI getUri(String path) {
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
     *
     * @return InstanceInfo
     */
    @Override
    public InstanceInfo getInstance() {
        String SERVICE_NAME = "edo-repository";
        List<InstanceInfo> instances
                = client.getApplication(SERVICE_NAME).getInstances();

        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));

        log.info(instance.getPort());

        return instance;
    }
}
