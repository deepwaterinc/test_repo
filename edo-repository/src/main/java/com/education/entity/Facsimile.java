package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Никита Бадеев
 * Класс, описывающий сущность факсимиле
 */
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "facsimile")
public class Facsimile extends BaseEntity {

    /**
     * Связь с работником
     */
    @ManyToOne
    @Column(name = "employee_id")
    private Employee employee;

    /**
     * Связь с департаментом
     */
    @ManyToOne
    @Column(name = "department_id")
    private Department department;

    /**
     * Связь с файлом
     */
    @OneToOne
    @Column(name = "file_id")
    private FilePool file;

    /**
     * Архивирован ли факсимиле
     */
    @Column(name = "isArchived")
    private boolean isArchived;
}
