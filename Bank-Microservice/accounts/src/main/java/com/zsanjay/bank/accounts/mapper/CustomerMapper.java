package com.zsanjay.bank.accounts.mapper;

import com.zsanjay.bank.accounts.dto.CustomerDto;
import com.zsanjay.bank.accounts.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}
