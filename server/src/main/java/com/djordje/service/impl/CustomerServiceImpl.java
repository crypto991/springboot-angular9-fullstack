package com.djordje.service.impl;


import com.djordje.exceptions.AccountServiceException;
import com.djordje.io.entity.Account;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.CustomerRepository;
import com.djordje.service.CustomerService;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.shared.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final AccountRepository accountRepository;

    public CustomerServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CustomerDTO getCustomer(String accountId) {

        Account account = accountRepository.findByAccountId(accountId);

        if (account ==null) {
            throw new AccountServiceException("Account doesnt exist!");
        }

        return CustomerMapper.INSTANCE.customerToCustomerDTO(account.getCustomer());
    }
}
