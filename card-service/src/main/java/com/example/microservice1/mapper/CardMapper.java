package com.example.microservice1.mapper;


import com.example.microservice1.dto.CardDto;
import com.example.microservice1.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto cardToCardDto(Card card);

    Card cardDtoToCard(CardDto cardDto);
}
