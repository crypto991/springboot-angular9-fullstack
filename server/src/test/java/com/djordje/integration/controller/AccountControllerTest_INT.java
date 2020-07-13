package com.djordje.integration.controller;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import com.djordje.service.AccountService;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.ui.model.request.AccountDetailsRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest_INT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

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
    @DisplayName("GET /accounts/userId")
    public void getUserAccounts() throws Exception {

        when(accountService.getUserAccounts(anyString())).thenReturn(accountDTOList);

        ResultActions result = mockMvc.perform(get("/accounts/{userId}", userId)
                .with(user(userId))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountDetailsRequestModel))
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andDo(print());


    }



    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}