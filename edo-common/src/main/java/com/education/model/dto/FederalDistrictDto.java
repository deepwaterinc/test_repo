package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;


@ApiModel("Класс FederalDistrictDto, dto для класса FederalDistrict.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FederalDistrictDto {
    @ApiModelProperty("Связь федерального округа и региона")
    private List<RegionDto> region;
    @ApiModelProperty("Название федерального округа")
    private String federalDistrictName;
    @ApiModelProperty("Сайт федерального округа")
    private String website;
}
