package com.education.email.service.impl;

import com.education.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Класс EmailServiceImpl.
 * Реализует EmailService.
 * Сервис класс для бизнес-логики отправки email
 */
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    /**
     * Объект для работы с почтой
     */
    private final JavaMailSender emailSender;

    /**
     * Метод для отправки email без attachment
     * @param toAddress - адрес получателя
     * @param subject - тема письма
     * @param message - текст письма
     */
    @Override
    public void sendSimpleEmail(String toAddress, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }

    /**
     * Метод для отправки email
     * @param toAddress - адрес получателя
     * @param subject - тема письма
     * @param message - текст письма
     */
    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
            throws MessagingException, FileNotFoundException {
        if (attachment != null) {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
            messageHelper.addAttachment("Filename", file);
            emailSender.send(mimeMessage);
        } else {
            sendSimpleEmail(toAddress, subject, message);
        }
    }
}
