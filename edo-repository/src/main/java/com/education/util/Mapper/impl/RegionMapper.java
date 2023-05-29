package com.education.util.Mapper.impl;

import com.education.entity.Region;
import com.education.model.dto.RegionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FederalDistrictMapper.class, builder = @Builder(disableBuilder = true))
public interface RegionMapper extends Mappable<Region, RegionDto> {
}
