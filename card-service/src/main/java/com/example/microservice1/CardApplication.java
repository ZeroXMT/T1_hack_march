package com.example.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // Add this import

@SpringBootApplication
//@EnableJpaRepositories("com.example.card_service.repository") // Add this annotation if your repository is not in the same package as the application class
public class CardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardApplication.class, args);
    }

}