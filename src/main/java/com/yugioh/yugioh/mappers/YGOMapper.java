package com.yugioh.yugioh.mappers;

import com.yugioh.yugioh.dtos.enums.SubCardType;
import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.models.Card;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface YGOMapper {

	List<Card> toCards(List<YGOCard> cards);

	@Mapping(target = "type", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "cardId", source = "id")
	@Mapping(target = "description", source = "desc")
	Card toCard(YGOCard ygoCard);

	@AfterMapping
	default void setTypes(@MappingTarget Card card, YGOCard ygoCard) {
		card.setSubType(SubCardType.getType(ygoCard.getType()));
		card.setType(card.getSubType().getCardType());
	}

}
