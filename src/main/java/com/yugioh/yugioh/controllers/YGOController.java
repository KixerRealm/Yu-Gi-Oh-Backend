package com.yugioh.yugioh.controllers;

import com.yugioh.yugioh.services.YGOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/ygo")
public class YGOController {

	private final YGOService ygoService;

	public YGOController(YGOService ygoService) {
		this.ygoService = ygoService;
	}

	@PostMapping("/replicate")
	public ResponseEntity<Void> replicateDatabase() throws IOException {
		ygoService.replicateDatabase();
		return ResponseEntity.ok().build();
	}
}
