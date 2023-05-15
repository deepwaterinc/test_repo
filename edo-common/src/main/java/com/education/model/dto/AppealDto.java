package com.education.model.dto;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToAnswer;
import com.education.model.enumEntity.EnumWayToReceive;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.ZonedDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.jfr.DataAmount;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

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

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
//    @JsonDeserialize(using = DeserializationFeature.class)
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

    @ApiModelProperty("Статус обращения")
    private EnumAppealStatus appealStatus;

    @ApiModelProperty("Способ получения обращения")
    private EnumWayToReceive sendingMethod;

    @ApiModelProperty("Способ получения ответа на обращение")
    private EnumWayToAnswer answeringMethod;

//    @ApiModelProperty("Регион")
//    private RegionDto region;
}