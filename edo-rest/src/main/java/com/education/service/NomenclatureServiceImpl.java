package com.education.service;

import com.education.model.dto.NomenclatureDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class NomenclatureServiceImpl implements NomenclatureService {
    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;

    private final String BASE_URL = "/api/service/nomenclature";

    private final String SERVICE_NAME = "edo-service";

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));
        log.info(instance.getPort());
        return instance;
    }

    private String getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .build().toString();
    }

    /**
     * Предоставляет список номенклатур из БД по индексу
     *
     * @return List of NomenclatureDto
     */
    @Override
    public List<NomenclatureDto> findByIndex(String index) {
        if (index.length() < 2) {
            return null;
        }
        InstanceInfo instanceInfo = getInstance();
        String path = "/search/?index=" + index;
        var uri = getURIByInstance(instanceInfo, path);
        NomenclatureDto[] response = TEMPLATE.getForObject(uri, NomenclatureDto[].class);
        return Arrays.asList(response);
    }
}
