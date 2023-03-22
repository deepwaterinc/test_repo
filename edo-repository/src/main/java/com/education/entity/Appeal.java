package com.education.entity;

import com.education.model.enumEntity.EnumAppealStatus;
import com.education.model.enumEntity.EnumWayToReceive;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;


/**
 * @author Alik Karibov.
 */
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "appeal")
public class Appeal extends BaseEntity {

    /**
     * Дата создания обращения
     */
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * Дата архивирования обращения
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Номер обращения
     */
    @Column(name = "number")
    private String number;

    /**
     * Описание обращения
     */
    @Column(name = "annotation", nullable = false)
    private String annotation;

    /**
     * Сотрудники, которые будут подписывать документ
     */

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_signer",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> signer;

    /**
     * Автор
     */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    /**
     * Получатели
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_addressee",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> addressee;

    /**
     * номенклатура, связанная с обращением
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomenclature_id")
    private Nomenclature nomenclature;

    /**
     * Авторы обращения
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_author",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    /**
     * хранилища файлов, связанных с обращением
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_file_pool", joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "file_pool_id", referencedColumnName = "id"))
    private List<FilePool> file;

    /**
     * Вопросы, связанные с обращением
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appeal_question",
            joinColumns = @JoinColumn(name = "appeal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private List<Question> question;

    /**
     * Статус обращения
     */
    @Column(name = "appeal_status")
    @Enumerated(EnumType.STRING)
    private EnumAppealStatus appealStatus;

    /**
     * Способ получения обращения
     */
    @Column(name = "way_to_receive")
    @Enumerated(EnumType.STRING)
    private EnumWayToReceive sendingMethod;
}
