package com.education.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Objects;

/**
 * @author Никита Бадеев
 */

@ApiModel("DTO для Facsimile")
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacsimileDTO that = (FacsimileDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
