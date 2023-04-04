package com.education.email.service;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AppealDto;
import com.education.model.dto.EmployeeDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class EmailQueueListener {

    private final EmailService emailService;
    private final EurekaClient EUREKA_CLIENT;
    private final String BASE_URL = "/api/rest/appeal";
    private final String SERVICE_NAME = "edo-rest";

    @RabbitListener(queues = RabbitConstant.addressCreateEmailQueue)
    public void createEmail(AppealDto appealDto) {
        sendNotificationOnAppeal(appealDto);
        log.log(Level.INFO, "Отправлено письмо");
    }

    /**
     * Получает инстанс edo-rest случайным методом
     */
    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * Возвращает URI по инстансу
     */
    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
    }

    /**
     * Метод для рассылки оповещений по почте всем адресантам и подписантам из обращения
     *
     * @param appealDto - обращение
     */
    private void sendNotificationOnAppeal(AppealDto appealDto) {
        try {
            var templateMessage = "Добрый день, %1$s!\n%2$s с номером " + appealDto.getNumber() + "\n" +
                    getURIByInstance(getInstance(), "/byId/" + appealDto.getId()).toURL();
            assembleAndSendEmail(appealDto.getAddressee(), templateMessage, "Для вас адресовано Обращение", appealDto.getNumber());
            assembleAndSendEmail(appealDto.getSigner(), templateMessage, "Вы являетесь Подписантом в Обращении", appealDto.getNumber());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        log.log(Level.INFO, "Все письма о создании нового обращения отправлены");
    }

    private void assembleAndSendEmail(List<EmployeeDto> employers, String template, String greeting, String id) {
        for (EmployeeDto emp : employers) {
            emailService.sendSimpleEmail(emp.getWorkEmail(), "Обращение № " + id,
                    template.formatted(emp.getFioNominative(),
                            greeting));
        }
    }
}
