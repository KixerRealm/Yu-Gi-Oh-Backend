package com.yugioh.yugioh.controllers;

import com.yugioh.yugioh.dtos.AccountDto;
import com.yugioh.yugioh.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/account")
@Api(value = "Account Controller", produces = APPLICATION_JSON_VALUE, tags = {"Account"})
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@ApiOperation(value = "Register a new account.", tags = {"Account"})
	@PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public void register(@RequestBody AccountDto dto) {
		accountService.register(dto);
	}

}
