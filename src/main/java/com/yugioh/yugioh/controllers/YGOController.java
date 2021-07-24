package com.yugioh.yugioh.controllers;

import com.yugioh.yugioh.services.YGOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/ygo")
@Api(value = "API for creating the base parameters & values of the project.", tags = {"YGO"})
public class YGOController {

	private final YGOService ygoService;

	public YGOController(YGOService ygoService) {
		this.ygoService = ygoService;
	}

	@PostMapping("/replicate")
	@ApiOperation(value = "Replicates the YGOProDeck database.", tags = {"YGO"})
	public ResponseEntity<Void> replicateDatabase() throws IOException {
		ygoService.replicateDatabase();
		return ResponseEntity.ok().build();
	}
}
