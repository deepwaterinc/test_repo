package com.education.service.resolution.impl;

import com.education.model.dto.ResolutionDto;
import com.education.service.resolution.ResolutionService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {

    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;

    private final String BASE_URL = "/api/repository/resolution";

    private final String SERVICE_NAME = "edo-repository";


    private InstanceInfo getInstance() {
        Application application = EUREKA_CLIENT.getApplication(SERVICE_NAME);
        InstanceInfo instance = application.getInstances().get(new Random().nextInt(application.size()));
        return instance;
    }

    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        URI uri = UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
        return uri;
    }

    @Override
    public ResolutionDto save(ResolutionDto resolution) {
        InstanceInfo instanceInfo = getInstance();
        var request = new RequestEntity(resolution, HttpMethod.POST, getURIByInstance(instanceInfo, ""));
        var response = TEMPLATE.exchange(request, ResolutionDto.class);
        return response.getBody();
    }

    @Override
    public void moveToArchive(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/toArchive/%s", id.toString()));
        TEMPLATE.put(uri, null);
    }

    @Override
    public ResolutionDto findById(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/byId/%s", id.toString()));
        ResolutionDto response = TEMPLATE.getForObject(uri, ResolutionDto.class);
        return response;
    }

    @Override
    public List<ResolutionDto> findAllById(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        URI uri = getURIByInstance(instanceInfo, String.format("/allById/%s", path));
        ResolutionDto[] response = TEMPLATE.getForObject(uri, ResolutionDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public ResolutionDto findByIdNotArchived(Long id) {
        InstanceInfo instanceInfo = getInstance();
        URI uri = getURIByInstance(instanceInfo, String.format("/notArchived/%s", id.toString()));
        ResolutionDto response = TEMPLATE.getForObject(uri, ResolutionDto.class);
        return response;
    }

    @Override
    public List<ResolutionDto> findAllByIdNotArchived(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        URI uri = getURIByInstance(instanceInfo, String.format("/allNotArchived/%s", path));
        ResolutionDto[] response = TEMPLATE.getForObject(uri, ResolutionDto[].class);
        return Arrays.asList(response);
    }
}
