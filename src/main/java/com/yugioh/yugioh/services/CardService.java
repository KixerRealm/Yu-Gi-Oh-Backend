package com.yugioh.yugioh.services;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.yugioh.yugioh.dtos.CardDto;
import com.yugioh.yugioh.dtos.helpers.OptionalBooleanBuilder;
import com.yugioh.yugioh.dtos.meta.PageRequestByExample;
import com.yugioh.yugioh.dtos.meta.PageResponse;
import com.yugioh.yugioh.mappers.CardMapper;
import com.yugioh.yugioh.models.Card;
import com.yugioh.yugioh.models.QCard;
import com.yugioh.yugioh.repositories.CardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

	private final CardRepository cardRepository;

	private final CardMapper cardMapper;

	public CardService(CardRepository cardRepository, CardMapper cardMapper) {
		this.cardRepository = cardRepository;
		this.cardMapper = cardMapper;
	}

	private BooleanExpression makeFilter(CardDto dto) {
		final QCard qCard = QCard.card;
		final OptionalBooleanBuilder opBuilder = OptionalBooleanBuilder.builder(qCard.isNotNull());
		return opBuilder.notEmptyAnd(qCard.id::eq, dto.getId())
				.notEmptyAnd(qCard.name::containsIgnoreCase, dto.getName())
				.build();
	}

	public PageResponse<CardDto> findAll(PageRequestByExample<CardDto> prbe) {
		CardDto dto = prbe.getExample();
		Pageable pageable = prbe.toPageable();
		Page<Card> page = dto != null ? cardRepository.findAll(makeFilter(dto), pageable) : Page.empty(pageable);
		List<CardDto> content = page.getContent().stream().map(cardMapper::toDto).collect(Collectors.toList());
		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
	}


	public void emptyDatabase() {
		cardRepository.deleteAll();
	}

	public void saveEntities(List<Card> cards) {
		cardRepository.saveAll(cards);
	}

	public List<String> getAllCardIds() {
		return cardRepository.findAll().stream().map(cardMapper::toDto).map(CardDto::getCardId).collect(Collectors.toList());
	}
}
