package com.djordje.ui.controller;

import com.djordje.service.impl.FarmServiceImpl;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.ui.model.request.FarmDetailsRequestModel;
import com.djordje.ui.model.response.FarmRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class FarmControllerTest {

    @InjectMocks
    FarmController controller;
    @Mock
    FarmServiceImpl farmService;

    FarmDTO farmDTO1;
    FarmDTO farmDTO2;

    FarmDetailsRequestModel farmDetailsRequestModel;

    String userId = "ajshdjsadhjsad";
    String accountId = "asdasgasda21";
    String farmId = "adhjsahdojsar3";


    List<FarmDTO> farmDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        farmDTO1 = new FarmDTO();
        farmDTO1.setId(1L);
        farmDTO1.setName("farm1");
        farmDTO1.setFarmId(farmId);

        farmDTO2 = new FarmDTO();
        farmDTO2.setId(2L);
        farmDTO2.setName("farm2");
        farmDTO2.setFarmId(farmId + "2");

        farmDTOList.addAll(Arrays.asList(farmDTO1, farmDTO2));

        farmDetailsRequestModel = new FarmDetailsRequestModel();
        farmDetailsRequestModel.setName("farm1");

    }

    @Test
    void getAccountFarms() {
        when(farmService.getFarmsForAccount(anyString())).thenReturn(farmDTOList);

        List<FarmRest> accountFarms = controller.getAccountFarms(accountId);

        assertEquals(accountFarms.size(), farmDTOList.size());
    }

    @Test
    void getAllFarmsForUserAccounts() {
        when(farmService.getFarmsForUserAccounts(anyString())).thenReturn(farmDTOList);

        List<FarmRest> allFarmsForUserAccounts = controller.getAllFarmsForUserAccounts(userId);

        assertEquals(allFarmsForUserAccounts.size(), farmDTOList.size());
    }

    @Test
    void getFarmById() {
        when(farmService.getFarmById(anyString())).thenReturn(farmDTO1);

        FarmRest farmById = controller.getFarmById(farmId);

        assertNotNull(farmById);

        assertEquals(farmById.getFarmId(), farmDTO1.getFarmId());
        assertEquals(farmById.getName(), farmDTO1.getName());


    }

    @Test
    void updateFarm() {
        when(farmService.updateFarm(any(FarmDTO.class), anyString())).thenReturn(farmDTO1);

        FarmRest farmRest = controller.updateFarm(farmDetailsRequestModel, farmId);

        assertNotNull(farmRest);
        assertEquals(farmRest.getName(), farmDTO1.getName());
    }

    @Test
    void deleteFarm() {
        when(farmService.deleteFarmById(anyString(),anyString())).thenReturn(farmDTOList);

        List<FarmRest> farmRests = controller.deleteFarm(accountId, farmId);

        assertEquals(farmRests.size(), farmDTOList.size());
    }

}