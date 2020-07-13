package com.djordje.ui.controller;


import com.djordje.exceptions.AccountServiceException;
import com.djordje.service.UserService;
import com.djordje.shared.dto.UserDTO;
import com.djordje.shared.mapper.UserMapper;
import com.djordje.ui.model.request.UserDetailRequestModel;
import com.djordje.ui.model.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRest createUser(@RequestBody UserDetailRequestModel userDetails) {

        if (userDetails.getFirstName().isEmpty())
            throw new AccountServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getMessage());

        UserDTO userDTO = UserMapper.INSTANCE.userDetailsToUserDto(userDetails);
        UserDTO createdUser = userService.create(userDTO);

        return UserMapper.INSTANCE.userDtoToUserRest(createdUser);
    }


}
