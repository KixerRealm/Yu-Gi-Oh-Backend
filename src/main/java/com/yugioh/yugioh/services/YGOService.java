package com.yugioh.yugioh.services;

import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.mappers.YGOMapper;
import com.yugioh.yugioh.services.clients.YGOClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class YGOService {

	private final YGOClient ygoClient;
	private final YGOMapper ygoMapper;

	private final MonsterCardService monsterCardService;
	private final SpellCardService spellCardService;

	public YGOService(YGOClient ygoClient, MonsterCardService monsterCardService,
					  SpellCardService spellCardService, YGOMapper ygoMapper) {
		this.ygoClient = ygoClient;
		this.monsterCardService = monsterCardService;
		this.spellCardService = spellCardService;
		this.ygoMapper = ygoMapper;
	}

	@Transactional
	public void replicateDatabase() {
		monsterCardService.emptyDatabase();
		spellCardService.emptyDatabase();

		List<YGOCard> cards = ygoClient.getAllCards();
		System.out.println(cards.stream().map(YGOCard::getType).distinct().collect(Collectors.toList()));
		monsterCardService.saveEntities(ygoMapper.toMonsterCards(cards.stream().filter(card -> card.getAtk() != null)));
		spellCardService.saveEntities(ygoMapper.toSpellCards(cards.stream().filter(card -> card.getAtk() == null)));
	}
}
