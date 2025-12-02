package com.zsanjay.bank.accounts.mapper;

import com.zsanjay.bank.accounts.dto.AccountsDto;
import com.zsanjay.bank.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountsMapper {

    AccountsDto toDto(Accounts accounts);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Accounts toEntity(AccountsDto accountsDto);
}
