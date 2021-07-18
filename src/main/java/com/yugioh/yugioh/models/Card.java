package com.yugioh.yugioh.models;


import com.yugioh.yugioh.dtos.enums.CardType;
import com.yugioh.yugioh.dtos.enums.SubCardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

// Take image using the Card ID property
@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Card {

	@Column(name = "id", length = 36)
	@GeneratedValue(generator = "strategy-uuid2")
	@GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
	@Id
	private String id;

	@Column(name = "card_id", length = 15, nullable = false)
	private String cardId;

	@Column(name = "type", length = 10, nullable = false)
	@Enumerated(EnumType.STRING)
	private CardType type;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "description", length = 5000, nullable = false)
	private String description;

	@Column(name = "sub_type", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private SubCardType subType;

	@Column(name = "atk", length = 5)
	private Integer atk;

	@Column(name = "def", length = 5)
	private Integer def;
}
