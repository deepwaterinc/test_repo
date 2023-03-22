package com.education.util.Mapper.impl;

import com.education.entity.Notification;
import com.education.model.dto.NotificationDto;
import com.education.util.Mapper.Mappable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends Mappable<Notification, NotificationDto> {
}
