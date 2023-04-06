package com.education.model.enumEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Способы получения ответа на обращение
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EnumWayToAnswer {
    BY_RUSSIAN_POST("Почта России"),
    BY_SMS("СМС"),
    BY_EMAIL("Электронная почта");

    private final String wayToAnswer;
}
