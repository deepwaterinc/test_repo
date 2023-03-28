package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = OAuth2ClientAutoConfiguration.class)
@EnableCaching
public class EdoRepositoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdoRepositoryApplication.class, args);
    }
}