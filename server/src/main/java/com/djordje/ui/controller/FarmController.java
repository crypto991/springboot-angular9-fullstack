package com.djordje.ui.controller;

import com.djordje.service.FarmService;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.shared.mapper.FarmMapper;
import com.djordje.ui.model.request.FarmDetailsRequestModel;
import com.djordje.ui.model.response.FarmRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping(path = "/{accountId}")
    public List<FarmRest> getAccountFarms(@PathVariable String accountId) {

        List<FarmDTO> farmDTOList = farmService.getFarmsForAccount(accountId);

        return FarmMapper.INSTANCE.farmDTOListToFarmRestList(farmDTOList);
    }

    @GetMapping(path = "/user/{userId}")
    public List<FarmRest> getAllFarmsForUserAccounts(@PathVariable String userId) {

        List<FarmDTO> farmDTOList = farmService.getFarmsForUserAccounts(userId);

        return FarmMapper.INSTANCE.farmDTOListToFarmRestList(farmDTOList);
    }

    @GetMapping(path = "/farm/{farmId}")
    public FarmRest getFarmById(@PathVariable String farmId) {

        FarmDTO farmDTO = farmService.getFarmById(farmId);

        return FarmMapper.INSTANCE.farmDTOToFarmRest(farmDTO);
    }

    @PutMapping(path = "/{farmId}")
    public FarmRest updateFarm(@RequestBody FarmDetailsRequestModel farmDetailsRequestModel,
                               @PathVariable String farmId) {

        FarmDTO farmDTO = FarmMapper.INSTANCE.farmDetailRequestToFarmDTO(farmDetailsRequestModel);

        FarmDTO updateFarm = farmService.updateFarm(farmDTO, farmId);

        return FarmMapper.INSTANCE.farmDTOToFarmRest(updateFarm);

    }


    @DeleteMapping(path = "/{accountId}/{farmId}")
    public List<FarmRest> deleteFarm(@PathVariable String accountId,
                                     @PathVariable String farmId) {

        List<FarmDTO> farmDTOList = farmService.deleteFarmById(accountId,farmId);

        return FarmMapper.INSTANCE.farmDTOListToFarmRestList(farmDTOList);

    }


    @PostMapping(path = "/{accountId}")
    public ResponseEntity<?> addFarm(@RequestBody FarmDetailsRequestModel farmDetailsRequestModel,
                                     @PathVariable String accountId) {

        FarmDTO farmDTO = FarmMapper.INSTANCE.farmDetailRequestToFarmDTO(farmDetailsRequestModel);
        farmService.addFarm(farmDTO, accountId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
