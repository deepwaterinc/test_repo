package com.education.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.ZonedDateTime;

@ApiModel("Dto для Department.class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @ApiModelProperty(
            notes = "Department id",
            dataType = "Long",
            example = "1",
            required = true
    )
    private Long id;

    @ApiModelProperty(
            notes = "Аббревиатура отдела",
            dataType = "String"
    )
    private String shortName;

    @ApiModelProperty(
            notes = "Название отдела полностью",
            dataType = "String"
    )
    private String fullName;

    @ApiModelProperty(
            notes = "Адрес отдела",
            dataType = "AddressDto.class"
    )
    private AddressDto address;

    @ApiModelProperty(
            notes = "Ключ внешней таблицы",
            value = "external_id",
            dataType = "Long"
    )
    private Long externalId;

    @ApiModelProperty(
            notes = "Телефонный номер",
            dataType = "String"
    )
    private String phone;

    @ApiModelProperty(
            notes = "Вышестоящий отдел",
            dataType = "DepartmentDto.class"
    )
    private DepartmentDto department;

    @ApiModelProperty(
            notes = "Дата открытия",
            dataType = "ZonedDateTime"
    )
    private ZonedDateTime creationDate;

    @ApiModelProperty(
            notes = "Дата закрытия",
            dataType = "ZonedDateTime"
    )
    private ZonedDateTime archivedDate;
}
