package com.zsanjay.bank.loans.mapper;

import com.zsanjay.bank.loans.dto.LoansDto;
import com.zsanjay.bank.loans.entity.Loans;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoansMapper {

    LoansDto toDto(Loans loans);
    Loans toEntity(LoansDto loansDto);
}
