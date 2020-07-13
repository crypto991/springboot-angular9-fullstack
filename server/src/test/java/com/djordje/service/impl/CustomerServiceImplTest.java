package com.djordje.service.impl;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.CustomerRepository;
import com.djordje.shared.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {


    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    AccountRepository accountRepository;

    Account account;
    Customer customer;

    String accountId = "asdasgasda21";
    String customerId = "asdnsalfgn12312";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        customer = new Customer();
        customer.setId(1L);
        customer.setCustomerId(customerId);
        customer.setName("customer1");

        account = new Account();
        account.setId(1L);
        account.setName("account1");
        account.setType("type1");
        account.setAccountId(accountId);
        account.setCustomer(customer);
    }

    @Test
    @DisplayName("Test - get customer")
    void getCustomer() {

        when(accountRepository.findByAccountId(anyString())).thenReturn(account);

        CustomerDTO customerDTO = customerService.getCustomer(accountId);

        assertNotNull(customerDTO);
        assertEquals(customerDTO.getName(), customer.getName());

        verify(accountRepository, times(1)).findByAccountId(accountId);



    }
}