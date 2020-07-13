package com.djordje.service;

import com.djordje.shared.dto.AccountDTO;
import com.djordje.shared.dto.FarmDTO;
import com.djordje.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDTO create(UserDTO userDTO);

    UserDTO getUserByUserId(String id);

    String getUserIDByEmail(String email);




}
