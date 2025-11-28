package com.zsanjay.bank.accounts.repository;

import com.zsanjay.bank.accounts.constants.AccountType;
import com.zsanjay.bank.accounts.entity.Accounts;
import com.zsanjay.bank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    List<Accounts> findByCustomer(Customer customer);

    Optional<Accounts> findByCustomerAndAccountType(Customer customer, AccountType accountType);

    @Transactional
    @Modifying
    void deleteByCustomer(Customer customer);
}
