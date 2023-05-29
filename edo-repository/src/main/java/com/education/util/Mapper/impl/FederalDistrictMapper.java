package com.education.util.Mapper.impl;

import com.education.entity.FederalDistrict;
import com.education.model.dto.FederalDistrictDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FederalDistrictMapper extends Mappable<FederalDistrict, FederalDistrictDto> {
}
