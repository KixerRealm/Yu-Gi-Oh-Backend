package com.yugioh.yugioh.dtos;

import lombok.Data;

@Data
public abstract class Identifiable {
	protected String id;

	boolean isIdSet() {
		return id != null && !id.isEmpty();
	}
}
