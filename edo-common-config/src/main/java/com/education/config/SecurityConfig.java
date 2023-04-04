package com.education.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс, где мы переопределяем конфигурацию безопасности
 */
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    @ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "true")
    public SecurityFilterChain filterChainAuthenticated(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/api/*")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated().and()
                .oauth2Login();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        return http.build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "security", name = "enabled", havingValue = "false")
    public SecurityFilterChain filterChainPermitAll(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest()
                .permitAll();
        return http.build();
    }
}
