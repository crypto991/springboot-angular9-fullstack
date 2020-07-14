package com.djordje.service.impl;


import com.djordje.exceptions.AccountServiceException;
import com.djordje.exceptions.FarmServiceException;
import com.djordje.exceptions.UserServiceException;
import com.djordje.io.entity.Account;
import com.djordje.io.entity.Farm;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.AccountRepository;
import com.djordje.io.repository.FarmRepository;
import com.djordje.io.repository.UserRepository;
import com.djordje.service.AccountService;
import com.djordje.service.FarmService;
import com.djordje.service.UserService;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.shared.dto.UserDTO;
import com.djordje.shared.mapper.FarmMapper;
import com.djordje.shared.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final Utils utils;

    public FarmServiceImpl(FarmRepository farmRepository, AccountRepository accountRepository, UserRepository userRepository, Utils utils) {
        this.farmRepository = farmRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public List<FarmDTO> getFarmsForUserAccounts(String id) {

        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity == null) {
            throw new UserServiceException("User with " + id + "doesn't exist!");
        }
        UserDTO userDTO = UserMapper.INSTANCE.userEntityToUserDTO(userEntity);

        List<FarmDTO> returnValue = new ArrayList<>();

        for (AccountDTO accountDTO : userDTO.getAccounts()) {
            returnValue.addAll(accountDTO.getFarms());
        }

        return returnValue;
    }

    @Override
    public FarmDTO getFarmById(String farmId) {

        Farm farm = farmRepository.findByFarmId(farmId);

        if (farm == null) {
            throw new FarmServiceException("Farm with " + farmId + "doesn't exist!");
        }

        return FarmMapper.INSTANCE.farmToFarmDTO(farm);
    }

    @Override
    public FarmDTO updateFarm(FarmDTO farmDTO, String farmId) {
        Farm farm = farmRepository.findByFarmId(farmId);

        if (farm == null) {
            throw new FarmServiceException("Farm with " + farmId + "doesn't exist!");
        }

        farm.setName(farmDTO.getName());

        return FarmMapper.INSTANCE.farmToFarmDTO(farmRepository.save(farm));
    }

    @Override
    public List<FarmDTO> getFarmsForAccount(String accountId) {

        List<Farm> farms = farmRepository.getAllByAccount_AccountId(accountId);

        return FarmMapper.INSTANCE.farmListToFarmDTOList(farms);
    }

    @Override
    public FarmDTO addFarm(FarmDTO farmDTO, String accountId) {
        Account account = accountRepository.findByAccountId(accountId);

        if (account == null) {
            throw new AccountServiceException("Account with " + accountId + "doesn't exist!");
        }

        Farm farm = FarmMapper.INSTANCE.farmDTOToFarm(farmDTO);
        farm.setFarmId(utils.generateFarmId(30));

        account.addFarm(farm);
        farm.setAccount(account);

        return FarmMapper.INSTANCE.farmToFarmDTO(farmRepository.save(farm));
    }

    @Override
    public List<FarmDTO> deleteFarmById(String accountId, String farmId) {

        Farm byFarmId = farmRepository.findByFarmId(farmId);

        if (byFarmId == null) {
            throw new FarmServiceException("Farm with " + farmId + "doesn't exist!");
        }

        farmRepository.deleteById(byFarmId.getId());

        return FarmMapper.INSTANCE.farmListToFarmDTOList(accountRepository.findByAccountId(accountId).getFarms());
    }

}
