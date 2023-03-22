package com.education.model.enumEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author Степан Ритман
 * Способ получения обащения
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum EnumWayToReceive {
    ON_PAPER("На бумаге"),
    BY_EMAIL("Через электронную почту"),
    IN_PERSON("Лично в приемной"),
    BY_PHONE("По телефону");

    private final String wayToReceive;
}
