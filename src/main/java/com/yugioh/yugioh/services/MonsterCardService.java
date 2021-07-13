package com.yugioh.yugioh.services;

import com.yugioh.yugioh.models.MonsterCard;
import com.yugioh.yugioh.repositories.MonsterCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterCardService {

	private final MonsterCardRepository monsterCardRepository;

	public MonsterCardService(MonsterCardRepository monsterCardRepository) {
		this.monsterCardRepository = monsterCardRepository;
	}

	public void emptyDatabase() {
		monsterCardRepository.deleteAll();
	}

	public void saveEntities(List<MonsterCard> cards) {
		monsterCardRepository.saveAll(cards);
	}
}
