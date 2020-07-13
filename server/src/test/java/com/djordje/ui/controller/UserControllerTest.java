package com.djordje.ui.controller;

import com.djordje.io.entity.Account;
import com.djordje.io.entity.Customer;
import com.djordje.io.entity.UserEntity;
import com.djordje.service.UserService;
import com.djordje.service.impl.UserServiceImpl;
import com.djordje.shared.dto.CustomerDTO;
import com.djordje.shared.dto.UserDTO;
import com.djordje.ui.model.request.UserDetailRequestModel;
import com.djordje.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    UserDTO userDTO;
    UserDetailRequestModel userDetailRequestModel;
    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        userDTO = new UserDTO();

        userDTO.setId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Jones");
        userDTO.setEmail("test@test.com");
        userDTO.setUserId(userId);
        userDTO.setEncryptedPassword(encryptedPassword);

        userDetailRequestModel = new UserDetailRequestModel();
        userDetailRequestModel.setEmail("test@test.com");
        userDetailRequestModel.setFirstName("John");
        userDetailRequestModel.setLastName("Jones");
        userDetailRequestModel.setPassword("123456");
    }

    @Test
    void createUser() {
        when(userService.create(any(UserDTO.class))).thenReturn(userDTO);

        UserRest userRest = userController.createUser(userDetailRequestModel);

        assertNotNull(userRest);
        assertEquals(userDetailRequestModel.getFirstName(), userRest.getFirstName());
        assertEquals(userDetailRequestModel.getLastName(), userRest.getLastName());
        assertEquals(userDetailRequestModel.getEmail(), userRest.getEmail());

        verify(userService, times(1)).create(any(UserDTO.class));
    }
}