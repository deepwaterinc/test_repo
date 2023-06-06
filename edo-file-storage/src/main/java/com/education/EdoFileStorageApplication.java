package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
public class EdoFileStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdoFileStorageApplication.class, args);
    }
}