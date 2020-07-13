package com.djordje.service.impl;

import com.djordje.exceptions.UserServiceException;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.UserRepository;
import com.djordje.service.UserService;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.UserDTO;
import com.djordje.shared.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, Utils utils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }


    @Override
    public UserDTO create(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new UserServiceException("Record with " + userDTO.getUserId() + " already exists!");
        }

        UserEntity userEntity = UserMapper.INSTANCE.userDtoToUserEntity(userDTO);

        userEntity.setEncryptedPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));

        return UserMapper.INSTANCE.userEntityToUserDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userId);
        }

        return UserMapper.INSTANCE.userEntityToUserDTO(userEntity);
    }


    @Override
    public String getUserIDByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return userEntity.getUserId();
    }


}
