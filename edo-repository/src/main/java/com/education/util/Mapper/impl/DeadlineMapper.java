package com.education.util.Mapper.impl;

import com.education.entity.Deadline;
import com.education.model.dto.DeadlineDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeadlineMapper extends Mappable<Deadline, DeadlineDto> {
}
