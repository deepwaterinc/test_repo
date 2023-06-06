package com.education.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * Представляет Департамент, Компанию, где работает работник.
 *
 * @author Сергей Иваненко
 * @version 1.0
 * @since 1.0
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "department")
public class Department extends BaseEntity {

    /**
     * Короткое имя или аббревиатура
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * Полное название
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * Адрес
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * Внешний идентификатор
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * Номер телефона
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Вышестоящий департамент
     */
    @OneToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    /**
     * Дата создания отдела
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата перевода в архив
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;
}
