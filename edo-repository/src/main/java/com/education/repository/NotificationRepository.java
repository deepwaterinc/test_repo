package com.education.repository;

import com.education.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Связь Notification с БД
 * @author Ильмир Хафизов
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
