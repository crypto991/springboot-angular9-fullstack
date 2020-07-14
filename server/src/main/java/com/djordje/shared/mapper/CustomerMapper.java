package com.djordje.shared.mapper;


import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.shared.dto.UserDTO;
import com.djordje.ui.model.request.CustomerDetailsRequestModel;
import com.djordje.ui.model.response.CustomerRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface CustomerMapper {



    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDTO customerToCustomerDTO(Customer customer);



}
