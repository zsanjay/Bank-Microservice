package com.zsanjay.bank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;

public record AccountsDto(Long accountNumber,
                          @NotEmpty(message = "AccountType cannot be a null or empty")
                          String accountType,
                          @NotEmpty(message = "Branch Address cannot be a null or empty")
                          String branchAddress) {

    public AccountsDto(
            @NotEmpty(message = "AccountType cannot be a null or empty") String accountType,
            @NotEmpty(message = "Branch Address cannot be a null or empty") String branchAddress) {
        this(null, accountType, branchAddress);
    }
}
