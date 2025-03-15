package org.hack.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf
                        .requireCsrfProtectionMatcher(
                                // Исключаем CSRF для API
                                ServerWebExchangeMatchers.pathMatchers("/api/**")
                        )
                )
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/public/**").permitAll() // Разрешаем доступ к публичным эндпоинтам
//                        .anyExchange().authenticated() // Требуем аутентификации для всех остальных запросов
//                )
                .httpBasic(httpBasicSpec -> httpBasicSpec.disable()) // Отключаем HTTP Basic
                .formLogin(formLoginSpec -> formLoginSpec.disable()) // Отключаем форму логина
                .build();
    }

}

