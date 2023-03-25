package com.education.testControllers;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.enumEntity.EnumAppealStatus;
import com.education.service.Appeal.CreatingAppealService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.EMPTY;

@Log4j2
@Service
@RequiredArgsConstructor
public class SaveAppealServiceImpl  {

    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    private final String BASE_URL = "/api/service/appeal";

    private final String SERVICE_NAME = "edo-service";

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        InstanceInfo instance = instances.get(0);
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

    public String callEdoServiceSaveT(){
        InstanceInfo instanceInfo = getInstance();
        var req = new Object();
        String uriByInstance = getURIByInstance(instanceInfo, "/tSave");
        var resp = TEMPLATE.getForObject(uriByInstance,String.class);

        return resp;
    }

}