package com.education.model.dto;

import com.education.model.enumEntity.EnumEmployment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ApiModel("Класс DTO для сущности Author(автор)")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthorDto {

    @ApiModelProperty("id автора")
    @NotBlank
    private Long id;

    @ApiModelProperty("Имя автора")
    private String firstName;

    @ApiModelProperty("Фамилия автора")
    private String lastName;

    @ApiModelProperty("Отчество автора")
    private String middleName;

    @ApiModelProperty("Адрес автора")
    private String address;

    @ApiModelProperty("СНИЛС автора")
    private String snils;

    @ApiModelProperty("Номер телефона автора")
    private String mobilePhone;

    @ApiModelProperty("Электронная почта автора")
    private String email;

    @ApiModelProperty("Рабочий статус автора")
    private EnumEmployment employment;

    @ApiModelProperty("ФИО автора в дательном падеже")
    private String fioDative;

    @ApiModelProperty("ФИО автора в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty("ФИО автора в именительном падеже")
    private String fioNominative;
}
