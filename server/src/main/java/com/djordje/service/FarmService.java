package com.djordje.service;

import com.djordje.shared.dto.FarmDTO;
import com.djordje.shared.dto.UserDTO;

import java.util.List;

public interface FarmService {

    FarmDTO addFarm(FarmDTO farmDTO, String accountId);

    List<FarmDTO> getFarmsForAccount(String accountId);

    List<FarmDTO> getFarmsForUserAccounts(String id);

    FarmDTO getFarmById(String farmId);

    FarmDTO updateFarm(FarmDTO farmDTO, String farmId);


    List<FarmDTO> deleteFarmById(String accountId, String farmId);

}
