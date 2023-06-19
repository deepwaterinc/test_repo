package com.education.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "deadline")
public class Deadline extends BaseEntity {


    /**
     * Дата дедлайна
     */
    @Column(name = "deadline_date")
    private ZonedDateTime date;

    /**
     * Причина переноса дедлайна
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Объект Resolution, связанный с дедлайнами
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolution_id")
    private Resolution resolution;
}
