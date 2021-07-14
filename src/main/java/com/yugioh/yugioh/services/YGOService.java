package com.yugioh.yugioh.services;

import com.yugioh.yugioh.dtos.ygo.YGOCard;
import com.yugioh.yugioh.mappers.YGOMapper;
import com.yugioh.yugioh.services.clients.YGOClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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
	public void replicateDatabase() throws IOException {
		cardService.emptyDatabase();

		// TODO: Check if needed, we might end up going with constant download/redownload of preview images as well
		List<YGOCard> cards = ygoClient.getAllCards();
		ygoClient.downloadSmallImages(cards.stream().map(YGOCard::getId).collect(Collectors.toList()));

		cardService.saveEntities(ygoMapper.toCards(cards));
	}
}
