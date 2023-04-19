package com.education.service.Appeal;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.model.enumEntity.EnumAppealStatus;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.EMPTY;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreatingAppealServiceImpl implements CreatingAppealService {

    private final RestTemplate TEMPLATE;
    private final EurekaClient EUREKA_CLIENT;
    private final EnumAppealStatus STATUS_FOR_NEW_APPEAL = EnumAppealStatus.NEW;

    private final String BASE_URL = "/api/service/appeal";

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

    @Override
    public AppealDto createAppeal(AppealDto appealDto) {
        InstanceInfo instanceInfo = getInstance();
        appealDto.setAppealStatus(STATUS_FOR_NEW_APPEAL);
        var response = TEMPLATE.postForObject(getURIByInstance(instanceInfo, EMPTY), appealDto, AppealDto.class);
        return response;
    }



    @Override
    public List<AppealAbbreviatedDto> findAllByIdEmployee(Long first, Long amount) {
        InstanceInfo instanceInfo = getInstance();
        String path = "/appealsByEmployee/?first=" +
                first +
                "&amount=" +
                amount;
        var uri = getURIByInstance(instanceInfo, path);
        AppealAbbreviatedDto[] response = TEMPLATE.getForObject(uri, AppealAbbreviatedDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public AppealDto findById(Long id) {
        var response = TEMPLATE.getForObject(getURIByInstance(getInstance(), String.format("/byId/%s", id.toString())), AppealDto.class);
        return response;
    }
}