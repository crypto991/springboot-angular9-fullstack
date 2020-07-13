package com.djordje.service.impl;

import com.djordje.exceptions.AccountServiceException;
import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.UserRepository;
import com.djordje.service.AccountService;
import com.djordje.service.UserService;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.UserDTO;
import com.djordje.shared.mapper.AccountMapper;
import com.djordje.shared.mapper.UserMapper;
import com.djordje.ui.model.request.AccountDetailsRequestModel;
import com.djordje.ui.model.response.AccountRest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final Utils utils;

    public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository, Utils utils) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.utils = utils;
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO, String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            throw new UsernameNotFoundException(userId);
        }

        Customer customer = new Customer();
        customer.setName(accountDTO.getCustomer().getName());
        customer.setCustomerId(utils.generateCustomerId(30));

        Account account = AccountMapper.INSTANCE.accountDTOToAccount(accountDTO);
        account.setCustomer(customer);
        customer.setAccount(account);
        account.setAccountId(utils.generateAccountId(30));

        userEntity.getAccounts().add(account);

        account.setUserEntity(userEntity);

        return AccountMapper.INSTANCE.acountToAccountDTO(accountRepository.save(account));
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO, String accountId) {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new AccountServiceException("Account doesn't exist!");
        }

        account.setName(accountDTO.getName());
        account.setType(accountDTO.getType());
        account.getCustomer().setName(accountDTO.getCustomer().getName());

        return AccountMapper.INSTANCE.acountToAccountDTO(accountRepository.save(account));
    }

    @Transactional
    @Override
    public List<AccountDTO> deleteAccountById(String userId, String accountId) {

        if (accountRepository.findByAccountId(accountId) == null) {
            throw new AccountServiceException("Account with " + accountId + "doesn't exist!");
        }

        accountRepository.deleteAccountByAccountId(accountId);

        return getUserAccounts(userId);

    }

    @Override
    public AccountDTO getAccountById(String accountId) {

        if(accountRepository.findByAccountId(accountId) == null) {
            throw new AccountServiceException("Account with " + accountId + "doesn't exist!");
        }

        Account account = accountRepository.findByAccountId(accountId);

        return AccountMapper.INSTANCE.acountToAccountDTO(account);
    }

    @Override
    public List<AccountDTO> getUserAccounts(String id) {

        UserEntity foundUser = userRepository.findByUserId(id);

        if (foundUser == null) {
            throw new AccountServiceException("User with id: " + id + "doesn't exist!");
        }

        UserDTO userDTO = UserMapper.INSTANCE.userEntityToUserDTO(foundUser);

        return userDTO.getAccounts();
    }


}
