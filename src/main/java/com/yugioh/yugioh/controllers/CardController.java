package com.yugioh.yugioh.controllers;

import com.yugioh.yugioh.dtos.CardDto;
import com.yugioh.yugioh.dtos.meta.PageRequestByExample;
import com.yugioh.yugioh.dtos.meta.PageResponse;
import com.yugioh.yugioh.services.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Api(value = "Card Controller", produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"Card"})
public class CardController {

	private final CardService cardService;

	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@ApiOperation(value = "Filter all cards with pagination & sort.", tags = {"Card"})
	@PostMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PageResponse<CardDto>> findAll(@RequestBody PageRequestByExample<CardDto> prbe) {
		return ResponseEntity.ok(cardService.findAll(prbe));
	}

	@ApiOperation(value = "Get all card ids.", tags = {"Card"})
	@GetMapping(value = "/ids", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getAllCardIds() {
		return ResponseEntity.ok(cardService.getAllCardIds());
	}
}
