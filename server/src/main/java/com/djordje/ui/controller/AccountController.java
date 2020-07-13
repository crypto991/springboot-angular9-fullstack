package com.djordje.ui.controller;

import com.djordje.service.AccountService;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.mapper.AccountMapper;
import com.djordje.ui.model.request.AccountDetailsRequestModel;
import com.djordje.ui.model.response.AccountRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountRest> getUserAccounts(@PathVariable String userId) {

        List<AccountDTO> accountDTOS = accountService.getUserAccounts(userId);

        return AccountMapper.INSTANCE.accountDTOListToAccountRestList(accountDTOS);
    }


    @GetMapping(path = "/account/{accountId}")
    public AccountRest getAccountById(@PathVariable String accountId) {

        AccountDTO accountDTO = accountService.getAccountById(accountId);

        return AccountMapper.INSTANCE.accountDTOToAccountRest(accountDTO);
    }



    @DeleteMapping(path = "/{userId}/{accountId}")
    public List<AccountRest> deleteAccount(@PathVariable String accountId,
                                           @PathVariable String userId) {

        List<AccountDTO> accountDTOS = accountService.deleteAccountById(userId, accountId);

        return AccountMapper.INSTANCE.accountDTOListToAccountRestList(accountDTOS);
    }



    @PostMapping(path = "/{userId}")
    public AccountRest createAccount(@RequestBody AccountDetailsRequestModel accountDetails,
                                     @PathVariable String userId) {

        AccountDTO accountDTO = AccountMapper.INSTANCE.accountDetailsToAccountDTO(accountDetails);


        return AccountMapper.INSTANCE.accountDTOToAccountRest(accountService.addAccount(accountDTO, userId));
    }



    @PutMapping(path = "/{accountId}")
    public AccountRest updateAccount(@RequestBody AccountDetailsRequestModel accountDetails,
                                     @PathVariable String accountId) {

        AccountDTO accountDTO = AccountMapper.INSTANCE.accountDetailsToAccountDTO(accountDetails);

        AccountDTO returnValue = accountService.updateAccount(accountDTO, accountId);

        return AccountMapper.INSTANCE.accountDTOToAccountRest(returnValue);

    }
}
