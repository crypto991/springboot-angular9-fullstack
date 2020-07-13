package com.djordje.service.impl;

import com.djordje.exceptions.UserServiceException;
import com.djordje.io.entity.UserEntity;
import com.djordje.io.repository.UserRepository;
import com.djordje.shared.Utils;
import com.djordje.shared.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    PasswordEncoder passwordEncoder;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    UserEntity userEntity;

    UserDTO userDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setFirstName("John");
        userEntity.setLastName("Jones");
        userEntity.setEmail("test@test.com");
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword(encryptedPassword);


        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Jones");
        userDTO.setPassword("12345678");
        userDTO.setEmail("test@test.com");

    }


    @Test
    @DisplayName("Test - CREATE User")
    void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateAccountId(anyInt())).thenReturn(userId);
        when(passwordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDTO storedUserDetails = userService.create(userDTO);

        assertNotNull(storedUserDetails);
        assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
        assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
        assertNotNull(storedUserDetails.getUserId());
        assertEquals(userEntity.getUserId(), storedUserDetails.getUserId());

        verify(utils, times(1)).generateUserId(30);
        verify(passwordEncoder, times(1)).encode("12345678");
        verify(userRepository, times(1)).save(any(UserEntity.class));

    }

    @Test
    @DisplayName("Test - UserServiceException")
    void testCreateUserException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        assertThrows(UserServiceException.class,
                () -> {
                    userService.create(userDTO);

                }
        );


    }

    @Test
    void testGetUserByUserId() {

        when(userRepository.findByUserId(anyString())).thenReturn(userEntity);

        UserDTO userDTO = userService.getUserByUserId("test@test.com");

        assertNotNull(userDTO);
        assertEquals("John", userDTO.getFirstName());
        assertEquals("John", userDTO.getFirstName());


    }

    @Test
    void testGetUserByUserId_UsernameNotFoundException() {
        when(userRepository.findByUserId(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userService.getUserByUserId("12123dfakdksad");

                }
        );

    }

}