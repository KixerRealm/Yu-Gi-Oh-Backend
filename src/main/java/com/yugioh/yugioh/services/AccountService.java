package com.yugioh.yugioh.services;

import com.yugioh.yugioh.dtos.AccountDto;
import com.yugioh.yugioh.mappers.AccountMapper;
import com.yugioh.yugioh.repositories.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private final AccountMapper accountMapper;
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;


	public AccountService(AccountMapper accountMapper, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		this.accountMapper = accountMapper;
		this.accountRepository = accountRepository;
		this.passwordEncoder = passwordEncoder;
	}

//	public AccountDto login(AccountDto dto) {
//		AccountDto acc = accountMapper.toDto(accountRepository.findAccountByUsername(dto.getUsername()));
//		if(!passwordEncoder.matches(acc.getPassword(), dto.getPassword())) {
//			throw new YugiohException(YugiohExceptionType.MISSING_ACCOUNT);
//		}
//
//		return acc;
//	}


	public void register(AccountDto dto) {
		// TODO: Maybe add checks that all types of characters are being used
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		accountRepository.save(accountMapper.toEntity(dto));
	}
}
