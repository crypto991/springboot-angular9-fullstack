package com.djordje.service.impl;

import com.djordje.exceptions.AccountServiceException;
import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.UserRepository;
import com.djordje.service.UserService;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    UserRepository userRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    Utils utils;

    UserEntity userEntity;
    Account account;
    AccountDTO accountDTO;
    CustomerDTO customerDTO;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    String accountId = "asdasgasda21";
    String customerId = "asdnsalfgn12312";

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setFirstName("John");
        userEntity.setLastName("Jones");
        userEntity.setEmail("test@test.com");
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword(encryptedPassword);

        customerDTO = new CustomerDTO();
        customerDTO.setName("customer1");
        customerDTO.setCustomerId(customerId);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCustomerId(customerId);
        customer.setName("customer1");

        account = new Account();
        account.setId(1L);
        account.setName("account1");
        account.setType("type1");
        account.setAccountId(accountId);
        account.setCustomer(customer);

        userEntity.getAccounts().add(account);
        account.setUserEntity(userEntity);
    }

    @Test
    @DisplayName("Test - Add Account")
    void testAddAccount() {
        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);
        when(utils.generateCustomerId(anyInt())).thenReturn(customerId);
        when(utils.generateAccountId(anyInt())).thenReturn(accountId);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setType("type1");
        accountDTO.setName("account1");
        accountDTO.setCustomer(customerDTO);

        AccountDTO storedAccount = accountService.addAccount(accountDTO, userId);

        assertNotNull(storedAccount);
        assertEquals(storedAccount.getName(), account.getName());
        assertEquals(storedAccount.getType(), account.getType());
        assertNotNull(storedAccount.getAccountId());
        assertEquals(storedAccount.getAccountId(), account.getAccountId());
        assertEquals(storedAccount.getCustomer().getName(), account.getCustomer().getName());
        assertEquals(storedAccount.getCustomer().getCustomerId(), account.getCustomer().getCustomerId());
        assertNotNull(storedAccount.getCustomer().getCustomerId());
    }

    @Test
    @DisplayName("Test - Add Account_UsernameNotFoundException")
    void testAddAccount_UsernameNotFoundException() {
        when(userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> {
                    accountService.addAccount(accountDTO, userId);
                }
        );

    }

    @Test
    @DisplayName("Test - Update Account_AccountServiceException")
    void testUpdateAccount_AccountServiceException() {

        when(userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(AccountServiceException.class,
                () -> {
                    accountService.updateAccount(accountDTO, userId);
                }
        );


    }

    @Test
    @DisplayName("Test - DeleteByAccountId")
    void testDeleteAccountById() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(account);
        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);

        accountService.deleteAccountById(userId, accountId);


        verify(userRepository, times(1)).findByUserId(userId);
        verify(accountRepository, times(1)).deleteAccountByAccountId(accountId);

    }

    @Test
    @DisplayName("Test - DeleteByAccountId_AccountServiceException")
    void testDeleteAccountById_AccountServiceException() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(null);

        assertThrows(AccountServiceException.class,
                () -> {
                    accountService.updateAccount(accountDTO, userId);
                }
        );

    }

    @Test
    @DisplayName("Test - getAccountById")
    void testGetAccountById() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(account);

        AccountDTO accountById = accountService.getAccountById(accountId);

        assertNotNull(accountById);
        assertEquals(accountById.getName(), account.getName());
        assertEquals(accountById.getType(), account.getType());
        assertEquals(accountById.getAccountId(), account.getAccountId());
        assertEquals(accountById.getCustomer().getName(), account.getCustomer().getName());
        assertEquals(accountById.getCustomer().getCustomerId(), account.getCustomer().getCustomerId());

    }

    @Test
    @DisplayName("Test - GET Account by Id _ AccountServiceException")
    void testGetAccountById_AccountServiceException() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(null);

        assertThrows(AccountServiceException.class,
                () -> {
                    accountService.updateAccount(accountDTO, userId);
                }
        );

    }

    @Test
    @DisplayName("Test - get User accounts")
    void testGetUserAccounts() {
        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);

        List<AccountDTO> userAccounts = accountService.getUserAccounts(userId);

        assertEquals(userAccounts.size(), userEntity.getAccounts().size());
        verify(userRepository, times(1)).findByUserId(userId);

    }


    @Test
    @DisplayName("Test - GET User accounts_AccountServiceException")
    void testGetUserAccounts_AccountServiceException() {
        when(userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(AccountServiceException.class,
                () -> {
                    accountService.updateAccount(accountDTO, userId);
                }
        );

    }

}