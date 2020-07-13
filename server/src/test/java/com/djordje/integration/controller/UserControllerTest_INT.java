package com.djordje.integration.controller;

import com.djordje.service.UserService;
import com.djordje.shared.dto.UserDTO;
import com.djordje.ui.model.request.UserDetailRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest_INT {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    UserDTO userDTO;
    UserDetailRequestModel userDetailRequestModel;
    String userId = "ajshdjsadhjsad";

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();

        userDTO.setId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Jones");
        userDTO.setEmail("test@gmail.com");
        userDTO.setUserId(userId);
        userDTO.setPassword("123456");

        userDetailRequestModel = new UserDetailRequestModel();
        userDetailRequestModel.setEmail("test@gmail.com");
        userDetailRequestModel.setFirstName("John");
        userDetailRequestModel.setLastName("Jones");
        userDetailRequestModel.setPassword("123456");

    }

    @Test
    @DisplayName("POST /users")
    public void createUser() throws Exception {

        when(userService.create(any(UserDTO.class))).thenReturn(userDTO);

        ResultActions result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDetailRequestModel))
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Jones"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"));

    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}