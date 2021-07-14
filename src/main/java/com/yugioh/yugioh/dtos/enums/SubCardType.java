package com.yugioh.yugioh.dtos.enums;

import com.yugioh.yugioh.exceptions.YugiohException;

import static com.yugioh.yugioh.exceptions.YugiohExceptionType.MISSING_SUB_TYPE;

public enum SubCardType {
	NORMAL_MONSTER("Normal Monster", CardType.MONSTER),
	EFFECT_MONSTER("Effect Monster", CardType.MONSTER),
	FLIP_EFFECT_MONSTER("Flip Effect Monster", CardType.MONSTER),
	FUSION_MONSTER("Fusion Monster", CardType.MONSTER),
	UNION_MONSTER("Union Monster", CardType.MONSTER),
	UNION_EFFECT_MONSTER("Union Effect Monster", CardType.MONSTER),
	TOON_MONSTER("Toon Monster", CardType.MONSTER),
	RITUAL_EFFECT_MONSTER("Ritual Effect Monster", CardType.MONSTER),
	RITUAL_MONSTER("Ritual Monster", CardType.MONSTER),
	SPELL("Spell Card", CardType.SPELL),
	SKILL("Skill Card", CardType.SPELL),
	TRAP("Trap Card", CardType.SPELL);

	private final String fullName;
	private final CardType cardType;

	SubCardType(String fullName, CardType cardType) {
		this.fullName = fullName;
		this.cardType = cardType;
	}

	public static SubCardType getType(String type) {
		for (SubCardType subType : values()) {
			if (type.equalsIgnoreCase(subType.getFullName())) {
				return subType;
			}
		}

		throw new YugiohException(MISSING_SUB_TYPE, type);
	}

	public String getFullName() {
		return fullName;
	}

	public CardType getCardType() {
		return cardType;
	}
}
