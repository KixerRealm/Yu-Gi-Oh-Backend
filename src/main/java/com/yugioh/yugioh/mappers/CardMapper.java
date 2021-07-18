package com.yugioh.yugioh.mappers;

import com.yugioh.yugioh.dtos.CardDto;
import com.yugioh.yugioh.models.Card;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {
	CardDto toDto(Card entity);
}
