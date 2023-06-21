package com.education.entity;

import com.education.model.enumEntity.EnumResolution;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * POJO класс, содержащий информацию о датах и работниках.
 *
 * @author Dmitrii Ermolenko
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "resolution")
public class Resolution extends BaseEntity {

    /**
     * Дата создания резолюции
     */
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    /**
     * Дата архивации
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Дата последнего события
     */
    @Column(name = "last_action_date")
    private ZonedDateTime lastActionDate;

    /**
     * Переменная отражающая тип решения
     */
    @Column(name = "resolution_name")
    @Enumerated(EnumType.STRING)
    private EnumResolution enumResolution;

    /**
     * Работник создавший резолюцию
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    /**
     * Работник подписывающий документ
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signer_id")
    private Employee signer;

    /**
     * Работники выполняющие решение
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "resolutions_employees",
            joinColumns = @JoinColumn(name = "resolution_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<Employee> executors;

    /**
     * Работник курирующий работу
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curator_id")
    private Employee curator;

    /**
     * Вопрос, к которому относится резолюция
     * У одного вопроса может быть несколько резолюций
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    /**
     * Дедлайн, который относится к резолюции
     * У одной резолюции может быть несколько дедлайнов
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resolution")
    private List<Deadline> deadlines;

}
