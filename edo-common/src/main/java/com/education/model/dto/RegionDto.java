package com.education.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.ZonedDateTime;

@ApiModel("Класс RegionDto, dto для класса Region.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegionDto {
    @ApiModelProperty("Идентификатор региона из внешних систем")
    private String externalId;
    @ApiModelProperty("Название региона")
    private String regionName;
    @ApiModelProperty("Дата архивации")
    private ZonedDateTime archivedDate;
    @ApiModelProperty("Количество")
    private String quantity;
    @ApiModelProperty("Федеральный округ")
    private FederalDistrictDto federalDistrict;
    @ApiModelProperty("Количество первичных отделений в регионе")
    private String numberOfPrimaryBranches;
    @ApiModelProperty("Количество местных отделений в регионе")
    private String numberOfLocalBranches;
}
