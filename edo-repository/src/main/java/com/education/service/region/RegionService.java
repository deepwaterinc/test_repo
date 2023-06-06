package com.education.service.region;

import com.education.entity.Region;
import com.education.model.dto.RegionDto;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    RegionDto save(RegionDto region);

    Optional<Region> findById(long id);

    List<Region> findAll();

    void delete (long id);
}
