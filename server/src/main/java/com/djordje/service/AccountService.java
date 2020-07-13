package com.djordje.service;

import com.djordje.shared.dto.AccountDTO;
import com.djordje.ui.model.request.AccountDetailsRequestModel;
import com.djordje.ui.model.response.AccountRest;

import java.util.List;

public interface AccountService {

    AccountDTO addAccount(AccountDTO accountDTO, String userId);

    List<AccountDTO> getUserAccounts(String id);

    AccountDTO getAccountById(String accountId);

    AccountDTO updateAccount(AccountDTO accountDTO, String accountId);

    List<AccountDTO> deleteAccountById(String userId, String accountId);
}
