package com.education.model.dto;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToReceive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ApiModel("Класс AppealDTO, dto для класса appeal.class")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AppealDto {
    @ApiModelProperty("Id обращения")
    private Long id;

    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Номер обращения")
    private String number;

    @ApiModelProperty("Описание обращения")
    private String annotation;

    @ApiModelProperty("Подписи")
    private List<EmployeeDto> signer;

    @ApiModelProperty("Автор")
    private EmployeeDto creator;

    @ApiModelProperty("Получатели")
    private List<EmployeeDto> addressee;

    @ApiModelProperty("Номенклатура")
    private NomenclatureDto nomenclature;

    @ApiModelProperty("Авторы обращения")
    private List<AuthorDto> authors;

    @ApiModelProperty("Файлы, связанные с обращением")
    private List<FilePoolDto> file;

    @ApiModelProperty("Вопросы, связанные с обращением")
    private List<QuestionDto> question;

    @ApiModelProperty("Тема обращения")
    private ThemeDto theme;

    @ApiModelProperty("Статус обращения")
    private EnumAppealStatus appealStatus;

    @ApiModelProperty("Способ получения обращения")
    private EnumWayToReceive sendingMethod;
}