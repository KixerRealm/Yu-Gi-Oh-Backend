package com.yugioh.yugioh.services;

import com.yugioh.yugioh.dtos.enums.SubCardType;
import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.mappers.YGOMapper;
import com.yugioh.yugioh.models.Card;
import com.yugioh.yugioh.services.clients.YGOClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YGOService {

	private final YGOClient ygoClient;
	private final YGOMapper ygoMapper;

	private final CardService cardService;

	public YGOService(YGOClient ygoClient, CardService cardService, YGOMapper ygoMapper) {
		this.ygoClient = ygoClient;
		this.cardService = cardService;
		this.ygoMapper = ygoMapper;
	}

	@Transactional
	public void replicateDatabase() {
		cardService.emptyDatabase();
		List<YGOCard> cards = ygoClient.getAllCards();
		List<Card> baseCards = ygoMapper.toCards(cards);
		baseCards = baseCards.stream()
				.filter(card -> card.getSubType() != SubCardType.SKILL)
				.collect(Collectors.toList());

//		ygoClient.downloadSmallImages(cards.stream().map(YGOCard::getId).collect(Collectors.toList()));

		cardService.saveEntities(baseCards);
	}
}
