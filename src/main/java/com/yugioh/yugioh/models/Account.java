package com.yugioh.yugioh.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Account {

	@Column(name = "id", length = 36)
	@GeneratedValue(generator = "strategy-uuid2")
	@GenericGenerator(name = "strategy-uuid2", strategy = "uuid2")
	@Id
	private String id;

	@Column(name = "username", length = 100)
	private String username;

	@Column(name = "password", length = 500)
	private String password;

}
