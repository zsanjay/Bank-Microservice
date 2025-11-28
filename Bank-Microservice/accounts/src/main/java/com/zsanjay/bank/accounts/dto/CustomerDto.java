package com.zsanjay.bank.accounts.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public record CustomerDto(@NotEmpty(message = "Name cannot be null or empty")
                          @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
                          String name,
                          @NotEmpty(message = "Email address cannot be null or empty")
                          @Email(message = "Invalid email address")
                          String email,
                          @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                          String mobileNumber,
                          List<AccountsDto> accounts) {

    public CustomerDto(String name, String email, String mobileNumber) {
        this(name, email, mobileNumber, List.of());
    }
}
