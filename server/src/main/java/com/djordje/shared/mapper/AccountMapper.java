package com.djordje.shared.mapper;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.ui.model.request.AccountDetailsRequestModel;
import com.djordje.ui.model.response.AccountRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel="spring")
public interface AccountMapper {



    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );


    AccountRest accountDTOToAccountRest(AccountDTO accountDTO);
    AccountDTO accountRestToAccountDTO(AccountRest accountRest);

    Account accountDTOToAccount(AccountDTO accountDTO);
    AccountDTO acountToAccountDTO(Account account);

    AccountDTO accountDetailsToAccountDTO(AccountDetailsRequestModel accountDetailsRequestModel);

    List<AccountRest> accountDTOListToAccountRestList(List<AccountDTO> accountDTOS);
    List<AccountDTO> accountListToAccountDTO(List<Account> accounts);

}
