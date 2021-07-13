package com.yugioh.yugioh.dtos.enums;

import com.yugioh.yugioh.exceptions.YugiohException;

import static com.yugioh.yugioh.exceptions.YugiohExceptionType.MISSING_SUB_TYPE;

public enum SubCardType {
	NORMAL_MONSTER("Normal Monster"),
	EFFECT_MONSTER("Effect Monster"),
	FLIP_EFFECT_MONSTER("Flip Effect Monster"),
	FUSION_MONSTER("Fusion Monster"),
	UNION_MONSTER("Union Monster"),
	UNION_EFFECT_MONSTER("Union Effect Monster"),
	TOON_MONSTER("Toon Monster"),
	RITUAL_EFFECT_MONSTER("Ritual Effect Monster"),
	RITUAL_MONSTER("Ritual Monster"),
	SPELL("Spell Card"),
	SKILL("Skill Card"),
	TRAP("Trap Card");

	private final String fullName;

	SubCardType(String fullName) {
		this.fullName = fullName;
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
}
