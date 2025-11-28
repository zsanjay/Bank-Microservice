package com.zsanjay.bank.accounts.service.impl;

import com.zsanjay.bank.accounts.constants.AccountType;
import com.zsanjay.bank.accounts.constants.AccountsConstants;
import com.zsanjay.bank.accounts.dto.AccountsDto;
import com.zsanjay.bank.accounts.dto.CustomerDto;
import com.zsanjay.bank.accounts.entity.Accounts;
import com.zsanjay.bank.accounts.entity.Customer;
import com.zsanjay.bank.accounts.exception.CustomerAlreadyExistsException;
import com.zsanjay.bank.accounts.exception.ResourceNotFoundException;
import com.zsanjay.bank.accounts.mapper.AccountsMapper;
import com.zsanjay.bank.accounts.mapper.CustomerMapper;
import com.zsanjay.bank.accounts.repository.AccountsRepository;
import com.zsanjay.bank.accounts.repository.CustomerRepository;
import com.zsanjay.bank.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountsMapper accountsMapper;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customerDto.mobileNumber());
        }
        Customer customer = customerMapper.toEntity(customerDto);
        log.info("Creating account for customer {}", customer);
        customer.setCreatedBy(AccountsConstants.USER);
        customer.setCreatedAt(LocalDateTime.now());
        Customer newCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(newCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber)
               .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
       List<Accounts> accountsList = accountsRepository.findByCustomer(customer);
       if (accountsList.isEmpty()) {
           throw new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString());
       }
       return new CustomerDto(customer.getName(), customer.getEmail(),
               customer.getMobileNumber(), accountsList.stream().map(accountsMapper::toDto).toList());
    }

    @Override
    public void updateAccount(CustomerDto customerDto) {
        List<AccountsDto> accountsDtos = customerDto.accounts();
        if(accountsDtos.isEmpty()) {
            throw new IllegalArgumentException("accounts cannot be empty");
        }
        if(accountsDtos.size() > 1) {
            throw new IllegalArgumentException("Only one account can be updated at a time");
        }
        Customer customer = customerRepository.findByMobileNumber(customerDto.mobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", customerDto.mobileNumber()));

        AccountsDto accountsDto = accountsDtos.getFirst();
        Accounts accounts = accountsRepository.findByCustomerAndAccountType(customer, AccountType.valueOf(accountsDto.accountType()))
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountType", accountsDto.accountType()));

        accounts.setBranchAddress(accountsDto.branchAddress());
        accountsRepository.save(accounts);
    }

    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomer(customer);
        customerRepository.deleteById(customer.getCustomerId());
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomer(customer);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy(AccountsConstants.USER);
        accounts.setAccountType(AccountType.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }
}
