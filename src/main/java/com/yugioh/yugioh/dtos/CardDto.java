package com.yugioh.yugioh.dtos;

import com.yugioh.yugioh.dtos.enums.CardType;
import com.yugioh.yugioh.dtos.enums.SubCardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CardDto extends Identifiable {
	private String cardId;
	private CardType type;
	private String name;
	private String description;
	private SubCardType subType;
	private Integer atk;
	private Integer def;
}
