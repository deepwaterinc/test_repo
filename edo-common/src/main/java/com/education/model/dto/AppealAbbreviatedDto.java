package com.education.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
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
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;
    @ApiModelProperty("Описание обращения")
    private String annotation;
    @ApiModelProperty("Автор")
    private EmployeeDto creator;

}
