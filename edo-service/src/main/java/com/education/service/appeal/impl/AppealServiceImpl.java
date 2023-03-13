package com.education.service.appeal.impl;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import com.education.service.appeal.AppealService;
import com.education.service.nomenclature.NomenclatureService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;

import org.apache.http.HttpHost;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.EMPTY;

@Service
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {

    private final RestTemplate TEMPLATE;

    private final EurekaClient EUREKA_CLIENT;

    private final NomenclatureService nomenclatureService;

    private final String BASE_URL = "/api/repository/appeal";

    private final String SERVICE_NAME = "edo-repository";

    /**
     * Получает инстанс случайным методом
     */
    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    private String getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .build().toString();
    }

    @Override
    public AppealDto save(AppealDto appealDto) {
        InstanceInfo instanceInfo = getInstance();

        final String NUMBER = nomenclatureService.getNumberFromTemplate(appealDto.getNomenclature());

        appealDto.setNumber(NUMBER);

        var response = TEMPLATE.postForObject(getURIByInstance(instanceInfo, EMPTY), appealDto, AppealDto.class);
        return response;
    }

    @Override
    public void moveToArchive(Long id) {
        InstanceInfo instanceInfo = getInstance();
        var uri = getURIByInstance(instanceInfo, String.format("/toArchive/%s", id.toString()));
        TEMPLATE.put(uri, null);
    }

    @Override
    public AppealDto findById(Long id) {
        InstanceInfo instanceInfo = getInstance();
        var uri = getURIByInstance(instanceInfo, String.format("/byId/%s", id.toString()));
        AppealDto response = TEMPLATE.getForObject(uri, AppealDto.class);
        return response;
    }

    @Override
    public List<AppealDto> findAllById(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        var uri = getURIByInstance(instanceInfo, String.format("/allById/%s", path));
        AppealDto[] response = TEMPLATE.getForObject(uri, AppealDto[].class);
        return Arrays.asList(response);
    }

    @Override
    public AppealDto findByIdNotArchived(Long id) {
        InstanceInfo instanceInfo = getInstance();
        var uri = getURIByInstance(instanceInfo, String.format("/notArchived/%s", id.toString()));
        AppealDto response = TEMPLATE.getForObject(uri, AppealDto.class);
        return response;
    }

    @Override
    public List<AppealDto> findAllByIdNotArchived(Iterable<Long> ids) {
        InstanceInfo instanceInfo = getInstance();
        String path = ids.toString().substring(1, ids.toString().length() - 1);
        var uri = getURIByInstance(instanceInfo, String.format("/allNotArchived/%s", path));
        AppealDto[] response = TEMPLATE.getForObject(uri, AppealDto[].class);
        return Arrays.asList(response);
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
}
