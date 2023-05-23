package com.education.model.dto;

import com.education.model.enumEntity.EnumApprovalUrgency;
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

    @ApiModelProperty("Дата создания листа согласования")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата перевода направления на согласование")
    private ZonedDateTime referralDate;

    @ApiModelProperty("Дата подписания")
    private ZonedDateTime signingDate;

    @ApiModelProperty("Дата перевода в архив")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Номер листа согласования")
    private String number;

}
