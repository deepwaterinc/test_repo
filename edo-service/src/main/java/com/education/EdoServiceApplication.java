package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = OAuth2ClientAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class EdoServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(EdoServiceApplication.class, args);

    }
}
