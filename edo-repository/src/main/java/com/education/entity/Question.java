package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Anton Latyshev.
 *
 * Класс Question.
 * Хранит краткое содержание обращений.
 */
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "question")
public class Question extends BaseEntity{
    /**
     * Дата создания обращения.
     */
    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    private ZonedDateTime creationDate;

    /**
     * Дата архивирования обращения.
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Краткое содержание обращения.
     */
    @Column(nullable = false)
    private String summary;

    /**
     * Объекты Resolution, связанные с вопросом
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<Resolution> resolutions;

    /**
     * Тема вопроса
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;
}
