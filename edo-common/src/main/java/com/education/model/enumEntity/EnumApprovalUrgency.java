package com.education.model.enumEntity;

/**
 * Срочность рассмотрения обращения.
 *
 * @author Ivan Chursinov
 */

public enum EnumApprovalUrgency {

    COMMON("Обычная"),
    URGENT("Срочная"),
    IMMEDIATE("Незамедлительная");

    private final String approvalUrgency;

    EnumApprovalUrgency(String approvalUrgency) {
        this.approvalUrgency = approvalUrgency;
    }

    public String getValue() {
        return approvalUrgency;
    }
}
