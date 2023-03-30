package com.education.service.email.impl;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AppealDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.NotificationDto;
import com.education.model.enumEntity.EnumNotification;
import com.education.service.email.EmailService;
import com.education.service.notification.NotificationService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class EmailServiceImpl implements EmailService {
    private final AmqpTemplate amqpTemplate;

    private final NotificationService notificationService;

    /**
     * Инициирует рассылку оповещений всем адресантам и подписантам и создает новую сущность Notification
     *
     * @param appealDto - обращение
     */
    @Override
    public void sendNotificationOnAppeal(AppealDto appealDto) {
        amqpTemplate.convertAndSend(RabbitConstant.exchange, RabbitConstant.addressCreateEmailQueue, appealDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEnumNotification(EnumNotification.EMAIL);
        notificationService.save(notificationDto);

        log.log(Level.INFO, "Отправлен запрос в очередь по рассылке оповещений");
    }

}
