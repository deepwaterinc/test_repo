package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel("Класс FederalDistrictDto, dto для класса FederalDistrict.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FederalDistrictDto {
    @ApiModelProperty("Идентификатор")
    private Long id;
    @ApiModelProperty("Название федерального округа")
    private String federalDistrictName;
    @ApiModelProperty("Сайт федерального округа")
    private String website;
}
