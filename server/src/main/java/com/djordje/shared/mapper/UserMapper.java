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
    List<UserRest> userDtosListToUserRestList(List<UserDTO> userDTOList);
    UserDTO userEntityToUserDTO(UserEntity userEntity);
    UserEntity userDtoToUserEntity(UserDTO userDTO);
    List<UserDTO> userEntityListToUserDTOList(List<UserEntity> userEntityList);
    List<UserEntity> userDtoListToUserEntityList(List<UserDTO> userDTOList);

}
