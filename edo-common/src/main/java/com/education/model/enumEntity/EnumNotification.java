package com.education.model.enumEntity;

public enum EnumNotification {

    EMAIL("Электронная почта"),
    PHONE("Номер телефона");
    private String value;

    EnumNotification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
