package com.education.model.enumEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Степан Ритман
 * Статусная модель обращения
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EnumAppealStatus {
    NEW("Новый"),
    REGISTERED("Зарегистрировано"),
    UNDER_CONSIDERATION("На рассмотрении"),
    IN_PROGRESS("В работе"),
    ARCHIVE("Архив"),
    AWAITING_DISPATCH("Ожидает отправки"),
    DONE("Выполнено");

    private final String appealStatus;
}
