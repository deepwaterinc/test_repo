package com.education.util.Mapper.impl;

import com.education.entity.Appeal;
import com.education.model.dto.AppealAbbreviatedDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppealAbbreviatedMapper extends Mappable<Appeal, AppealAbbreviatedDto> {
}
