package com.djordje.service.impl;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.Farm;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.FarmRepository;
import com.djordje.io.repository.UserRepository;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.shared.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class FarmServiceImplTest {

    @InjectMocks
    FarmServiceImpl farmService;

    @Mock
    UserRepository userRepository;
    @Mock
    FarmRepository farmRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    Utils utils;


    UserEntity userEntity;
    UserDTO userDTO;
    Account account;
    Farm farm;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    String accountId = "asdasgasda21";
    String customerId = "asdnsalfgn12312";
    String farmId = "adhjsahdojsar3";


    List<Farm> farms = new ArrayList<>();

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

        farm = new Farm();
        farm.setId(1L);
        farm.setName("farm1");
        farm.setFarmId(farmId);

        farm.setAccount(account);
        account.addFarm(farm);

        userEntity.getAccounts().add(account);
        account.setUserEntity(userEntity);

        farms.add(farm);
    }

    @Test
    @DisplayName("Test - get Farms For User Accounts")
    void getFarmsForUserAccounts() {
        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);

        List<FarmDTO> farms = farmService.getFarmsForUserAccounts(userId);

        List<Farm> entityFarms = new ArrayList<>();

        for (Account account : userEntity.getAccounts()) {
            entityFarms.addAll(account.getFarms());
        }

        assertEquals(farms.size(), entityFarms.size());
        verify(userRepository, times(1)).findByUserId(userId);

    }

    @Test
    @DisplayName("Test - get Farm by farmId")
    void getFarmById() {
        when(farmRepository.findByFarmId(anyString())).thenReturn(farm);

        FarmDTO farmById = farmService.getFarmById(farmId);

        assertNotNull(farmById);
        assertEquals(farmById.getName(), farm.getName());
        assertEquals(farmById.getFarmId(), farm.getFarmId());
    }

    @Test
    @DisplayName("Test - UPDATE farm")
    void updateFarm() {
        FarmDTO farmDTO = new FarmDTO();
        farmDTO.setName("farm2");
        Farm farm2 = new Farm();
        farm2.setName("farm2");
        when(farmRepository.findByFarmId(farmId)).thenReturn(farm);
        when(farmRepository.save(any(Farm.class))).thenReturn(farm2);

        FarmDTO changed = farmService.updateFarm(farmDTO, farmId);

        assertNotNull(changed);
        verify(farmRepository, times(1)).findByFarmId(farmId);
        verify(farmRepository, times(1)).save(farm);

    }

    @Test
    @DisplayName("Test - GET Farms for Account")
    void getFarmsForAccount() {
        when(farmRepository.getAllByAccount_AccountId(accountId)).thenReturn(farms);

        List<FarmDTO> farmsForAccount = farmService.getFarmsForAccount(accountId);

        assertEquals(farmsForAccount.size(), farms.size());
        verify(farmRepository, times(1)).getAllByAccount_AccountId(accountId);

    }

    @Test
    @DisplayName("Test - ADD farm")
    void addFarm() {
        when(accountRepository.findByAccountId(accountId)).thenReturn(account);
        when(utils.generateFarmId(anyInt())).thenReturn(farmId);
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);

        FarmDTO farmDTO = new FarmDTO();
        farmDTO.setName("farm2");
        farmDTO.setFarmId(farmId);

        FarmDTO saved = farmService.addFarm(farmDTO, accountId);

        assertNotNull(saved);
        assertEquals(saved.getName(), farm.getName());
        assertEquals(saved.getFarmId(), farm.getFarmId());

        verify(accountRepository, times(1)).findByAccountId(accountId);
        verify(utils, times(1)).generateFarmId(30);

    }

}