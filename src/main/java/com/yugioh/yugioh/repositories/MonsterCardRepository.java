package com.yugioh.yugioh.repositories;

import com.yugioh.yugioh.models.MonsterCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonsterCardRepository extends JpaRepository<MonsterCard, String> {
}
