package com.yugioh.yugioh.controllers;

import com.yugioh.yugioh.dtos.CardDto;
import com.yugioh.yugioh.dtos.meta.PageRequestByExample;
import com.yugioh.yugioh.dtos.meta.PageResponse;
import com.yugioh.yugioh.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@PostMapping("/find")
	public ResponseEntity<PageResponse<CardDto>> findAll(@RequestBody PageRequestByExample<CardDto> prbe) {
		return ResponseEntity.ok(cardService.findAll(prbe));
	}
}
