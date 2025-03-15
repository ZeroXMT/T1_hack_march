package org.hack.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class TokenValidationFilter extends AbstractGatewayFilterFactory<TokenValidationFilter.Config> {

    public TokenValidationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String token = request.getHeaders().getFirst("Authorization");

            if (true) {
                return chain.filter(exchange);
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        });
    }

    public static class Config{
    }

    public boolean isValidToken(String token) {
        try {
            // Создаем URL для запроса
            URL url = new URL("http://localhost:8081/api/v1/validate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Настраиваем соединение
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            // Формируем тело запроса
            String jsonInputString = "{\"accessToken\": \"" + token + "\"}";

            // Отправляем запрос
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Получаем ответ
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Читаем ответ
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    // Предположим, что ответ содержит JSON с полем "valid"
                    // Пример ответа: {"valid": true}
                    return response.toString().contains("\"valid\": true");
                }
            } else {
                // Если код ответа не 200 OK, считаем токен невалидным
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
