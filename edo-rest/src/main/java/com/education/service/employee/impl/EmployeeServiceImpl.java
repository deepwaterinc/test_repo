package com.education.service.employee.impl;


import com.education.model.dto.EmployeeDto;
import com.education.service.employee.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {
    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;

    private final String BASE_URL = "/api/service/employee";

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
     * Метод, производящий поиск в таблице сущностей Employee по введенным символам
     * @param fio
     * @return
     */
    @Override
    public List<EmployeeDto> findAllByLastNameLikeOrderByLastName(String fio) {
        if (fio.length() < 3) {
            return null;
        }
        InstanceInfo instanceInfo = getInstance();
        String path = "/byFIO/?fio=" + fio;
        var uri = getURIByInstance(instanceInfo, path);
        EmployeeDto[] response = TEMPLATE.getForObject(uri, EmployeeDto[].class);
        return Arrays.asList(response);
    }
}
