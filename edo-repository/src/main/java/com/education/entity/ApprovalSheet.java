package com.education.entity;

import com.education.model.enumEntity.EnumApprovalUrgency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * Сущность: лист согласования, который содержит основные данные для согласования обращения.
 *
 * @author Ivan Chursinov
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "approval_sheet")
public class ApprovalSheet extends BaseEntity {

    /**
     * Срочность рассмотрения: Обычная, Срочная, Незамедлительная
     */
    @Column(name = "approval_urgency", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumApprovalUrgency approvalUrgency;

    /**
     * Ссылка на родительский лист согласования
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_approval_sheet")
    private ApprovalSheet parentApprovalSheet;

    /**
     * Комментарий инициатора
     */
    @Column(name = "initiator_comment")
    private String initiatorComment;

    /**
     * Дата создания листа согласования
     */
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * Дата перевода направления на согласование
     */
    @Column(name = "referral_date")
    private ZonedDateTime referralDate;

    /**
     * Дата подписания
     */
    @Column(name = "signing_date")
    private ZonedDateTime signingDate;

    /**
     * Дата перевода в архив
     */
    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    /**
     * Номер листа согласования
     */
    @Column(name = "number", nullable = false)
    private String number;

}
