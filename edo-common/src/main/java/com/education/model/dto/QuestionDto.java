package com.education.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.ZonedDateTime;
import java.util.List;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@ApiModel("Класс QuestionDto - DTO для Question.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class QuestionDto {
    @ApiModelProperty("Id обращения")
    private Long id;

    @ApiModelProperty("Дата создания обращения")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивирования обращения")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Краткое содержание обращения")
    private String summary;

    @ApiModelProperty("Список резолюций по вопросу")
    @JsonInclude(NON_NULL)
    private List<ResolutionDto> resolutions;

    @ApiModelProperty("Тема, к которой относится вопрос")
    private ThemeDto theme;
}
