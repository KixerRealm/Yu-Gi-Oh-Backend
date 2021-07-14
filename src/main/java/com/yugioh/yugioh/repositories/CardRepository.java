package com.yugioh.yugioh.repositories;

import com.querydsl.core.types.Predicate;
import com.yugioh.yugioh.models.Card;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CardRepository extends JpaRepository<Card, String>, QuerydslPredicateExecutor<Card> {

	@NonNull
	Page<Card> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

}
