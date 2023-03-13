package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Data
@ApiModel("Класс AppealAbbreviatedDto, dto для класса appeal.class")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppealAbbreviatedDto {

    @ApiModelProperty("Номер обращения")
    private String number;
    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;
    @ApiModelProperty("Описание обращения")
    private String annotation;
    @ApiModelProperty("Автор")
    private EmployeeDto creator;

}
