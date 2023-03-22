package com.education.util.Mapper.impl;

import com.education.entity.Resolution;
import com.education.model.dto.ResolutionDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResolutionMapper extends Mappable<Resolution, ResolutionDto> {

    //создано для ухода от бесконечного цикла при маппинге
    @Override
    @Mapping(target = "question.resolutions", ignore = true)
    ResolutionDto toDto(Resolution resolution);
}
