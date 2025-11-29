package com.zsanjay.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public record CustomerDto(
        @Schema(
                description = "Name of the customer",
                example = "Sanjay Mehta"
        )
        @NotEmpty(message = "Name cannot be null or empty")
        @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
        String name,

        @Schema(
                description = "Email address of the customer",
                example = "zsanjayofficial@gmail.com"
        )
        @NotEmpty(message = "Email address cannot be null or empty")
        @Email(message = "Invalid email address")
        String email,

        @Schema(
                description = "Mobile number of the customer",
                example = "1234567890"
        )
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        String mobileNumber,

        @Schema(
                description = "Account details of the customer"
        )
        List<AccountsDto> accounts) {

    public CustomerDto(String name, String email, String mobileNumber) {
        this(name, email, mobileNumber, List.of());
    }
}
