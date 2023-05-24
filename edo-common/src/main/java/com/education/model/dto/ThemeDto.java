package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@ApiModel("Класс ThemeDto, DTO для класса Theme.class")
public class ThemeDto {

    @ApiModelProperty("id темы обращения")
    private Long id;

    @ApiModelProperty("Название темы обращения")
    private String name;

    @ApiModelProperty("Дата создания темы")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивации темы")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Код темы")
    private String code;

    @ApiModelProperty("Родительская тема")
    private ThemeDto parentThemeDto;
}