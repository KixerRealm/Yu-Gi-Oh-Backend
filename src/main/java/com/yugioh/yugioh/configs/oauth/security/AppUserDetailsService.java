package com.yugioh.yugioh.configs.oauth.security;

import com.yugioh.yugioh.models.Account;
import com.yugioh.yugioh.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppUserDetailsService implements UserDetailsService {

	// TODO: Implement after implementing Account services (and possibly roles)
	private static final Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);

	private final AccountRepository accountRepository;

	public AppUserDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		log.info("Loading user by username: {}", s);
		Account user = accountRepository.findAccountByUsername(s);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
		}
		log.info("Found account: {}", user.getUsername());

		List<GrantedAuthority> authorities = new ArrayList<>();
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
		log.info("User details: {}", userDetails);
		return userDetails;
	}
}
