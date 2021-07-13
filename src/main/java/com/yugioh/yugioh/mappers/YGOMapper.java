package com.yugioh.yugioh.mappers;

import com.yugioh.yugioh.dtos.enums.SubCardType;
import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.models.Card;
import com.yugioh.yugioh.models.MonsterCard;
import com.yugioh.yugioh.models.SpellCard;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface YGOMapper {

	List<MonsterCard> toMonsterCards(Stream<YGOCard> cards);

	List<SpellCard> toSpellCards(Stream<YGOCard> cards);

	@Mapping(target = "type", ignore = true)
	@Mapping(target = "description", source = "desc")
	MonsterCard toMonsterCard(YGOCard ygoCard);

	@Mapping(target = "type", ignore = true)
	@Mapping(target = "description", source = "desc")
	SpellCard toSpellCard(YGOCard ygoCard);

	@AfterMapping
	default void setSubType(@MappingTarget Card card, YGOCard ygoCard) {
		card.setSubType(SubCardType.getType(ygoCard.getType()));
	}

}
