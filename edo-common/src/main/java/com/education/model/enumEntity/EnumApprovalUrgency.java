package com.education.model.enumEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Срочность рассмотрения обращения.
 *
 * @author Ivan Chursinov
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EnumApprovalUrgency {

    COMMON("Обычная"),
    URGENT("Срочная"),
    IMMEDIATE("Незамедлительная");

    private final String approvalUrgency;

}
