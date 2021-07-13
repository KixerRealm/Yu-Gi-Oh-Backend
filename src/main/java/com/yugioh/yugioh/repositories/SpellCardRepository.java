package com.yugioh.yugioh.repositories;

import com.yugioh.yugioh.models.SpellCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpellCardRepository extends JpaRepository<SpellCard, String> {
}
