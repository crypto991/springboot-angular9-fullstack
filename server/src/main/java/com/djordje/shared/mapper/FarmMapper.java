package com.djordje.shared.mapper;

import com.djordje.io.entity.Farm;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.ui.model.request.FarmDetailsRequestModel;
import com.djordje.ui.model.response.FarmRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface FarmMapper {


    FarmMapper INSTANCE = Mappers.getMapper( FarmMapper.class );

    FarmRest farmDTOToFarmRest(FarmDTO farmDTO);
    FarmDTO farmDetailRequestToFarmDTO(FarmDetailsRequestModel farmDetailsRequestModel);
    List<FarmRest> farmDTOListToFarmRestList(List<FarmDTO> farmDTOList);
    List<FarmDTO> farmListToFarmDTOList(List<Farm> list);
    Farm farmDTOToFarm(FarmDTO farmDTO);
    FarmDTO farmToFarmDTO(Farm farm);
}
