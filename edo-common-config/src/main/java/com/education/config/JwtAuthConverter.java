package com.education.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс-конвертер для извлечения claims и roles из JWT токена
 */
@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private final JwtAuthConverterProperties properties;

    public JwtAuthConverter(JwtAuthConverterProperties properties) {
        this.properties = properties;
    }

    /**
     * Метод создает new JwtAuthenticationToken из токена jwt.
     * JwtAuthenticationToken в Spring Security используется для аутентификации
     * пользователей на основе веб-токена JSON (JWT). Он отвечает за проверку JWT,
     * создание аутентифицированного Authentication объекта и авторизацию доступа к защищенным ресурсам.
     * @param jwt Jwt
     * @return new JwtAuthenticationToken
     */
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    /**
     * Метод вытаскивает имя claim из токена jwt.
     * JwtAuthenticationToken в Spring Security используется для аутентификации
     * пользователей на основе веб-токена JSON (JWT). Он отвечает за проверку JWT,
     * создание аутентифицированного Authentication объекта и авторизацию доступа к защищенным ресурсам.
     * @param jwt Jwt
     * @return String
     */
    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (properties.getPrincipal() != null) {
            claimName = properties.getPrincipal();
        }
        return jwt.getClaim(claimName);
    }

    /**
     * Метод вытаскивает роли из токена jwt.
     * JwtAuthenticationToken в Spring Security используется для аутентификации
     * пользователей на основе веб-токена JSON (JWT). Он отвечает за проверку JWT,
     * создание аутентифицированного Authentication объекта и авторизацию доступа к защищенным ресурсам.
     * @param jwt Jwt
     * @return String
     */
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (resourceAccess == null
                || (resource = (Map<String, Object>) resourceAccess.get(properties.getResource())) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {
            return Set.of();
        }
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
