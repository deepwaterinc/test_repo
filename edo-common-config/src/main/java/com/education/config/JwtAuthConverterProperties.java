package com.education.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


/**
 * Класс для передачи настроек из конфига
 */
@Data
@Validated
@Configuration
public class JwtAuthConverterProperties {
    @Value("${jwt.auth.converter.resource: edo-project-client}")
    private String resource;
    @Value("${jwt.auth.converter.principal: preferred_username}")
    private String principal;
}

