package com.zsanjay.bank.accounts.service;

import com.zsanjay.bank.accounts.dto.CustomerDto;

public interface AccountsService {
    /**
     *
     * @param customerDto : CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    void updateAccount(CustomerDto customerDto);

    void deleteAccount(String mobileNumber);
}
