package com.education.model.dto;

import com.education.model.enumEntity.EnumNotification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("Класс NotificationDto, dto для класса Notification.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NotificationDto {

    @ApiModelProperty("Id оповещения")
    private Long id;

    @ApiModelProperty("Id работника, для которого уведомление")
    private EmployeeDto employee;

    @ApiModelProperty("Тип оповещения")
    private EnumNotification enumNotification;
}
