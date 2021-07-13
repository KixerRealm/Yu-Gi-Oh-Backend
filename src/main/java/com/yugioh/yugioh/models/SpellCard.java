package com.yugioh.yugioh.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SPELL")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class SpellCard extends Card {
}
