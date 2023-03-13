package com.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel("Класс AddressDto, dto для класса Address.class")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDto {
    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("Полный адрес")
    private String fullAddress;
    @ApiModelProperty("Улица")
    private String street;
    @ApiModelProperty("Номер дома")
    private String house;
    @ApiModelProperty("Индекс")
    private String index;
    @ApiModelProperty("Корпус")
    private String housing;
    @ApiModelProperty("Строение")
    private String building;
    @ApiModelProperty("Город")
    private String city;
    @ApiModelProperty("Регион")
    private String region;
    @ApiModelProperty("Страна")
    private String country;
    @ApiModelProperty("Этаж")
    private String flat;


}
