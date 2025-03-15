package com.example.microservice1.service;

import com.example.microservice1.dto.CardDto;
import com.example.microservice1.entity.Card;
import com.example.microservice1.mapper.CardMapper;
//import com.example.microservice1.mapper.Mapper;
import com.example.microservice1.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardDto getCardById(UUID productId) {
        Optional<Card> card = cardRepository.findById(productId);
        return card.map(cardMapper::cardToCardDto)
                .orElse(null); // Handle not found case properly (e.g., throw exception)
    }

    @Override
    public List<CardDto> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(cardMapper::cardToCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public CardDto createCard(CardDto cardDto) {
        Card card = cardMapper.cardDtoToCard(cardDto);
        Card savedCard = cardRepository.save(card);
        return cardMapper.cardToCardDto(savedCard);
    }

    @Override
    @Transactional
    public CardDto updateCard(UUID productId, CardDto cardDto) {
        Optional<Card> cardOptional = cardRepository.findById(productId);
        if (cardOptional.isPresent()) {
            Card existingCard = cardOptional.get();
            cardMapper.updateCardFromDto(cardDto, existingCard);
            return cardMapper.cardToCardDto(existingCard);
        } else {
            return null; // Handle not found case properly (e.g., throw exception)
        }
    }

    @Override
    public void deleteCard(UUID productId) {
        cardRepository.deleteById(productId);
    }
}