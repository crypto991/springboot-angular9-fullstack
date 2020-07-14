package com.djordje.shared.mapper;

import com.djordje.io.entity.UserEntity;
import com.djordje.shared.dto.UserDTO;
import com.djordje.ui.model.request.UserDetailRequestModel;
import com.djordje.ui.model.response.UserRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO userDetailsToUserDto(UserDetailRequestModel userDetailRequestModel);
    UserRest userDtoToUserRest(UserDTO userDTO);
    UserDTO userEntityToUserDTO(UserEntity userEntity);
    UserEntity userDtoToUserEntity(UserDTO userDTO);


}
