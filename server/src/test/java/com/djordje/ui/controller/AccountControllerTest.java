package com.djordje.ui.controller;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import com.djordje.service.AccountService;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.ui.model.request.AccountDetailsRequestModel;
import com.djordje.ui.model.response.AccountRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    UserEntity userEntity;
    Account account;
    AccountDTO accountDTO;
    AccountDetailsRequestModel accountDetailsRequestModel;
    CustomerDTO customerDTO;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    String accountId = "asdasgasda21";
    String customerId = "asdnsalfgn12312";

    List<AccountDTO> accountDTOList = new ArrayList<>();

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

        accountDetailsRequestModel = new AccountDetailsRequestModel();
        accountDetailsRequestModel.setName("account1");
        accountDetailsRequestModel.setType("type1");

        userEntity.getAccounts().add(account);
        account.setUserEntity(userEntity);

        accountDTO = new AccountDTO();
        accountDTO.setName("account1");
        accountDTO.setType("type1");
        accountDTO.setAccountId(accountId);
        accountDTO.setCustomer(customerDTO);

        accountDTOList.add(accountDTO);
    }

    @Test
    void getUserAccounts() {
        when(accountService.getUserAccounts(anyString())).thenReturn(accountDTOList);

        List<AccountRest> userAccounts = accountController.getUserAccounts(userId);

        assertEquals(userAccounts.size(), accountDTOList.size());

    }

    @Test
    void getAccountById() {
        when(accountService.getAccountById(anyString())).thenReturn(accountDTO);

        AccountRest accountById = accountController.getAccountById(accountId);

        assertNotNull(accountById);
        assertEquals(accountById.getAccountId(), accountDTO.getAccountId());
        assertEquals(accountById.getCustomer().getCustomerId(), accountDTO.getCustomer().getCustomerId());
        assertEquals(accountById.getType(), accountDTO.getType());
    }

    @Test
    void deleteAccount() {
        when(accountService.deleteAccountById(anyString(), anyString())).thenReturn(accountDTOList);

        List<AccountRest> accountRests = accountController.deleteAccount(userId, accountId);

        assertEquals(accountRests.size(), accountDTOList.size());

        verify(accountService, times(1)).deleteAccountById(anyString(), anyString());
    }

    @Test
    void createAccount() {
        when(accountService.addAccount(any(AccountDTO.class), anyString())).thenReturn(accountDTO);

        AccountRest accountRest = accountController.createAccount(accountDetailsRequestModel, accountId);

        assertNotNull(accountRest);
        assertEquals(accountRest.getName(), accountDTO.getName());
        assertEquals(accountRest.getType(), accountDTO.getType());
        assertEquals(accountRest.getCustomer().getCustomerId(), accountDTO.getCustomer().getCustomerId());

    }

    @Test
    void updateAccount() {
        when(accountService.updateAccount(any(AccountDTO.class), anyString())).thenReturn(accountDTO);

        AccountRest accountRest = accountController.updateAccount(accountDetailsRequestModel, accountId);

        assertNotNull(accountRest);
        assertEquals(accountRest.getName(), accountDTO.getName());
        assertEquals(accountRest.getType(), accountDTO.getType());
        assertEquals(accountRest.getCustomer().getCustomerId(), accountDTO.getCustomer().getCustomerId());
    }
}