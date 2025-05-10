package com.example.microservice1.mapper;


import com.example.microservice1.dto.CardDto;
import com.example.microservice1.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CardMapper {

    CardDto cardToCardDto(Card card);

    Card cardDtoToCard(CardDto cardDto);

    void updateCardFromDto(CardDto cardDto, @MappingTarget Card existingCard);
}
