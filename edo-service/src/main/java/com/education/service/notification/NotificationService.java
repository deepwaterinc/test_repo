package com.education.service.notification;

import com.education.model.dto.NotificationDto;

import java.util.List;

/**
 * @author Хафизов Ильмир
 * <p>
 * Представляет список операций над оповещением пользователя
 */

public interface NotificationService {

    /**
     * Сохранение оповещений в БД
     *
     * @param notificationDto
     */
    void save(NotificationDto notificationDto);

    /**
     * Сохранение оповещений в БД
     * @param notificationSet
     */
    void saveAll(List<NotificationDto> notificationSet);

    /**
     * Удаление оповещений в БД по id
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Предоставляет NotificationDto оповещений из БД по id
     *
     * @param id
     * @return
     */
    NotificationDto findById(Long id);

    /**
     * Предоставляет список NotificationDto оповещений из БД по id
     *
     * @param id
     * @return
     */
    List<NotificationDto> findAllById(List<Long> id);
}
