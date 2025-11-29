package com.zsanjay.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public record AccountsDto(
        @Schema(
                description = "Account Number of Bank Account"
        )
        Long accountNumber,
        @NotEmpty(message = "AccountType cannot be a null or empty")
        @Schema(
                description = "Account Type of Bank Account",
                example = "SAVINGS"
        )
        String accountType,
        @NotEmpty(message = "Branch Address cannot be a null or empty")
        @Schema(
                description = "Branch Address of Bank Account"
        )
        String branchAddress
) {

    public AccountsDto(
            @NotEmpty(message = "AccountType cannot be a null or empty") String accountType,
            @NotEmpty(message = "Branch Address cannot be a null or empty") String branchAddress) {
        this(null, accountType, branchAddress);
    }
}
