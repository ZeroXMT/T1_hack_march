package com.example.microservice1.service;


import com.example.microservice1.dto.CardDto;
import java.util.List;
import java.util.UUID;

public interface CardService {

    CardDto getCardById(UUID productId);

    List<CardDto> getAllCards();

    CardDto createCard(CardDto cardDto);

    CardDto updateCard(UUID productId, CardDto cardDto);

    void deleteCard(UUID productId);
}