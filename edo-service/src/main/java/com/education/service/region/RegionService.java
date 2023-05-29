package com.education.service.region;

import com.education.model.dto.RegionDto;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public interface RegionService {

    RegionDto save(RegionDto region);

    RegionDto findById(Long id);

    List<RegionDto> findAll();
    List<RegionDto> getRegionDtos(String path);
    URI getUri(String path);

    InstanceInfo getInstance();


}
