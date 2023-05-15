package com.education.model.dto;

import com.education.model.enumEntity.EnumApprovalUrgency;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * @author Ivan Chursinov
 */

@ApiModel("Класс ApprovalSheetDto, dto для класса ApprovalSheet.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApprovalSheetDto {

    @ApiModelProperty("ID листа согласования")
    private Long id;

    @ApiModelProperty("Срочность рассмотрения")
    private EnumApprovalUrgency approvalUrgency;

    @ApiModelProperty("Ссылка на родительский лист согласования")
    private ApprovalSheetDto parentApprovalSheetDto;

    @ApiModelProperty("Комментарий инициатора")
    private String initiatorComment;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата создания листа согласования")
    private ZonedDateTime creationDate;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата перевода направления на согласование")
    private ZonedDateTime referralDate;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата подписания")
    private ZonedDateTime signingDate;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @ApiModelProperty("Дата перевода в архив")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Номер листа согласования")
    private String number;

}
