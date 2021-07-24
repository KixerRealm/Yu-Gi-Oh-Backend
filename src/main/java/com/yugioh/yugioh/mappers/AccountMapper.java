package com.yugioh.yugioh.mappers;

import com.yugioh.yugioh.dtos.AccountDto;
import com.yugioh.yugioh.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {
	AccountDto toDto(Account entity);

	Account toEntity(AccountDto dto);
}
