package com.yugioh.yugioh.services;

import com.yugioh.yugioh.models.SpellCard;
import com.yugioh.yugioh.repositories.SpellCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellCardService {

	private final SpellCardRepository spellCardRepository;

	public SpellCardService(SpellCardRepository spellCardRepository) {
		this.spellCardRepository = spellCardRepository;
	}

	public void emptyDatabase() {
		spellCardRepository.deleteAll();
	}

	public void saveEntities(List<SpellCard> cards) {
		spellCardRepository.saveAll(cards);
	}
}
