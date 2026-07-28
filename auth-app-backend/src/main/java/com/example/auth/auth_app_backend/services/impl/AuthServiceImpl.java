package com.example.auth.auth_app_backend.services.impl;

import com.example.auth.auth_app_backend.dtos.UserDTO;
import com.example.auth.auth_app_backend.services.AuthService;
import com.example.auth.auth_app_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        UserDTO userDTO1 = userService.createUser(userDTO);
        return userDTO1;
    }
}

