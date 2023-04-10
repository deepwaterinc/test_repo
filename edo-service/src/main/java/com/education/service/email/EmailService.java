package com.education.service.email;

import com.education.model.dto.AppealDto;

public interface EmailService {
    void sendNotificationOnAppeal(AppealDto appealDto);
}
