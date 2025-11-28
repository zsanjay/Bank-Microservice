package com.zsanjay.bank.accounts.mapper;

import com.zsanjay.bank.accounts.dto.AccountsDto;
import com.zsanjay.bank.accounts.entity.Accounts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    AccountsDto toDto(Accounts accounts);
    Accounts toEntity(AccountsDto accountsDto);
}
