package com.zsanjay.bank.accounts.mapper;

import com.zsanjay.bank.accounts.dto.CustomerDto;
import com.zsanjay.bank.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "accounts", ignore = true)
    CustomerDto toDto(Customer customer);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    Customer toEntity(CustomerDto customerDto);
}
