package com.yugioh.yugioh.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MONSTER")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class MonsterCard extends Card {

	@Column(name = "atk", length = 5)
	private Integer atk;

	@Column(name = "def", length = 5)
	private Integer def;
}
