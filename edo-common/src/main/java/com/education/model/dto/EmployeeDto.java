package com.education.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Степан Ритман
 */
@ApiModel("DTO для класса Employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    @ApiModelProperty("Id работника")
    private Long id;

    @ApiModelProperty("Имя работника")
    private String firstName;

    @ApiModelProperty("Фамилия работника")
    private String lastName;

    @ApiModelProperty("Отчество работника")
    private String middleName;

    @ApiModelProperty("Адрес")
    private String address;

    @ApiModelProperty("URL-адрес фото")
    private String photoUrl;

    @ApiModelProperty("ФИО в дательном падеже")
    private String fioDative;

    @ApiModelProperty("ФИО в именительном падеже")
    private String fioNominative;

    @ApiModelProperty("ФИО в родительном падеже")
    private String fioGenitive;

    @ApiModelProperty("Внешний идентификатор")
    private String externalId;

    @ApiModelProperty("Мобильный номер телефона")
    private String phone;

    @ApiModelProperty("Рабочий номер телефона")
    private String workPhone;

    @ApiModelProperty("Рабочая почта")
    private String workEmail;

    @ApiModelProperty("Дата рождения")
    private ZonedDateTime birthDate;

    @ApiModelProperty("Имя пользователя")
    private String username;

    @ApiModelProperty("Дата создания")
    private ZonedDateTime creationDate;

    @ApiModelProperty("Дата архивирования")
    private ZonedDateTime archivedDate;

    @ApiModelProperty("Типы оповещения, которые выбрали пользователи")
    private List<NotificationDto> notification;
}
