package com.education.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Степан Ритман
 * Класс, описывающий сущность работника
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "employee")
public class Employee extends BaseEntity {

    /**
     * Имя
     */
    @Column(name = "first_name", length = 20)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name", length = 20)
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 20)
    private String middleName;

    /**
     * Адрес
     */
    @Column(name = "address", length = 200)
    private String address;

    /**
     * URL-адрес фото
     */
    @Column(name = "photo_url", length = 300)
    private String photoUrl;

    /**
     * ФИО в дательном падеже
     */
    @Column(name = "fio_dative", length = 60)
    private String fioDative;

    /**
     * ФИО в именительном падеже
     */
    @Column(name = "fio_nominative", length = 60)
    private String fioNominative;

    /**
     * ФИО в родительном падеже
     */
    @Column(name = "fio_genitive", length = 60)
    private String fioGenitive;

    /**
     * Внешний идентификатор
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * Мобильный номер телефона
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Рабочий номер телефона
     */
    @Column(name = "work_phone", length = 20)
    private String workPhone;

    /**
     * Рабочая почта
     */
    @Column(name = "work_email", length = 20)
    private String workEmail;

    /**
     * Дата рождения
     */
    @Column(name = "birth_date")
    private ZonedDateTime birthDate;

    /**
     * Имя пользователя
     */
    @Column(name = "username", length = 20)
    private String username;

    /**
     * Дата создания
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата архивирования
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Типы оповещения, которые выбрали пользователи
     */
    /**
     * Типы оповещения, которые выбрали пользователи
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    private List<Notification> notification;
}
