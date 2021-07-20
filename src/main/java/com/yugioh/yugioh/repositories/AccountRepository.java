package com.yugioh.yugioh.repositories;

import com.yugioh.yugioh.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

	Account findAccountByUsername(String username);

}
