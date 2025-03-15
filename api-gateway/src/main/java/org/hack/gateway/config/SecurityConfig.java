package org.hack.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
    TokenValidationFilter tokenValidationFilter;
    public SecurityConfig(TokenValidationFilter tokenValidationFilter) {
        this.tokenValidationFilter = tokenValidationFilter;
    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable()
                )
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/**").permitAll() // Разрешаем доступ к публичным эндпоинтам
                )
                .httpBasic(httpBasicSpec -> httpBasicSpec.disable()) // Отключаем HTTP Basic
                .formLogin(formLoginSpec -> formLoginSpec.disable()) // Отключаем форму логина
                .build();
    }

}

