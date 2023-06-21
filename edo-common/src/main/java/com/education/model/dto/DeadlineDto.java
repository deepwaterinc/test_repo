package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@ApiModel("Класс DeadlineDto - DTO для Deadline.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DeadlineDto {

    @ApiModelProperty("id дедлайна")
    private Long id;

    @ApiModelProperty("Дата дедлайна")
    private ZonedDateTime date;

    @ApiModelProperty("Причина переноса")
    private String comment;

    @ApiModelProperty("Резолюция, к которой относится дедлайн")
    private ResolutionDto resolution;

}
