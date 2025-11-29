package com.zsanjay.bank.cards.mapper;

import com.zsanjay.bank.cards.dto.CardsDto;
import com.zsanjay.bank.cards.entity.Cards;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardsMapper {

    Cards toEntity(CardsDto cardsDto);
    CardsDto toDto(Cards cards);
}
