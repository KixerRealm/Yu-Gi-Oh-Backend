package com.yugioh.yugioh.configs.oauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);

//	private final AccountRepository accountRepository;
//
//	private final RoleRepository roleRepository;
//
//	public AppUserDetailsService(AccountRepository accountRepository, RoleRepository roleRepository) {
//		this.accountRepository = accountRepository;
//		this.roleRepository = roleRepository;
//	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		log.info("Loading user by username: {}", s);
//		Account user = accountRepository.getByLogin(s);

//		if (user == null) {
//			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
//		}
//		log.info("Found account: {}", user.getLogin());
//
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRoleName())));
//		UserDetails userDetails = new User(user.getLogin(), user.getPassword(), authorities);
//		log.info("User details: {}", userDetails);
//		return userDetails;
		return null;
	}
}
