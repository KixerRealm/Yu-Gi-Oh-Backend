package com.yugioh.yugioh.models;


import com.yugioh.yugioh.dtos.enums.CardType;
import com.yugioh.yugioh.dtos.enums.SubCardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Card {

	@Column(name = "id", length = 36)
	@GeneratedValue(generator = "strategy-uuid2")
	@GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
	@Id
	private String id;

	@Column(name = "card_id", length = 15)
	private String cardId;

	@Column(name = "type", length = 10, updatable = false, insertable = false)
	@Enumerated(EnumType.STRING)
	private CardType type;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "sub_type", length = 30)
	@Enumerated(EnumType.STRING)
	private SubCardType subType;

	@Column(name = "main_image_url")
	private String mainImageUrl;

	@Column(name = "preview_url")
	private String previewUrl;
}
