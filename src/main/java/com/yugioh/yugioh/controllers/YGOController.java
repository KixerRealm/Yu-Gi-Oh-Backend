package com.yugioh.yugioh.controllers;

import com.yugioh.yugioh.services.YGOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ygo")
public class YGOController {

	private final YGOService ygoService;

	public YGOController(YGOService ygoService) {
		this.ygoService = ygoService;
	}

	@PostMapping("/replicate")
	public ResponseEntity<Void> replicateDatabase() {
		ygoService.replicateDatabase();
		return ResponseEntity.ok().build();
	}
}
