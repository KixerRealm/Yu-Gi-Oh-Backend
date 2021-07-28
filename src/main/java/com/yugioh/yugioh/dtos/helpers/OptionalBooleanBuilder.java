package com.yugioh.yugioh.dtos.helpers;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class OptionalBooleanBuilder {

	private final BooleanExpression predicate;

	private OptionalBooleanBuilder(BooleanExpression predicate) {
		this.predicate = predicate;
	}

	public static OptionalBooleanBuilder builder(BooleanExpression predicate) {
		return new OptionalBooleanBuilder(predicate);
	}

	public <T> OptionalBooleanBuilder notNullAnd(Function<T, BooleanExpression> expressionFunction, T value) {
		if (value != null) {
			return new OptionalBooleanBuilder(predicate.and(expressionFunction.apply(value)));
		}
		return this;
	}

	public <T> OptionalBooleanBuilder andNoArgsPredicate(Supplier<BooleanExpression> expressionFunction) {
		return new OptionalBooleanBuilder(predicate.and(expressionFunction.get()));
	}

	public <T> OptionalBooleanBuilder notNullOr(Function<T, BooleanExpression> expressionFunction, T value) {
		if (value != null) {
			return new OptionalBooleanBuilder(predicate.or(expressionFunction.apply(value)));
		}
		return this;
	}

	public OptionalBooleanBuilder notEmptyAnd(Function<String, BooleanExpression> expressionFunction, String value) {
		if (!StringUtils.isEmpty(value)) {
			return new OptionalBooleanBuilder(predicate.and(expressionFunction.apply(value)));
		}
		return this;
	}

	public <T> OptionalBooleanBuilder notEmptyAnd(Function<Collection<T>, BooleanExpression> expressionFunction, Collection<T> collection) {
		if (!CollectionUtils.isEmpty(collection)) {
			return new OptionalBooleanBuilder(predicate.and(expressionFunction.apply(collection)));
		}
		return this;
	}

	public BooleanExpression build() {
		return predicate;
	}
}

