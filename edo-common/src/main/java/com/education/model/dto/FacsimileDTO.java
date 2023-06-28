package com.education.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Никита Бадеев
 */

@ApiModel("DTO для Facsimile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacsimileDTO {

    @ApiModelProperty("Id Факсимиле")
    private Long id;

    @ApiModelProperty("Связь с работником")
    private EmployeeDto employee;

    @ApiModelProperty("Связь с департаментом")
    private DepartmentDto department;

    @ApiModelProperty("Связь с файлом")
    private FilePoolDto file;

    @ApiModelProperty("Архивирован ли факсимиле")
    boolean isArchived;
}
