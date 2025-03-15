package com.example.microservice1.controller;

import com.example.microservice1.dto.CardDto;
import com.example.microservice1.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService; // Inject the service

    @GetMapping("/{productId}")
    public ResponseEntity<CardDto> getCardById(@PathVariable UUID productId) {
        CardDto cardDto = cardService.getCardById(productId);
        if (cardDto != null) {
            return ResponseEntity.ok(cardDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cardDtos = cardService.getAllCards();
        return ResponseEntity.ok(cardDtos);
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        CardDto createdCard = cardService.createCard(cardDto);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<CardDto> updateCard(@PathVariable UUID productId, @RequestBody CardDto cardDto) {
        CardDto updatedCard = cardService.updateCard(productId, cardDto);
        if (updatedCard != null) {
            return ResponseEntity.ok(updatedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID productId) {
        cardService.deleteCard(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}