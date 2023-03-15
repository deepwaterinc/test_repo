package com.education;

import com.education.service.fileConvertion.FileConversionService;
import com.education.service.fileConvertion.impl.FileConversionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EdoServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(EdoServiceApplication.class, args);

    }
}
