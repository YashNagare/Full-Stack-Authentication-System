package com.example.auth.auth_app_backend.services;

import com.example.auth.auth_app_backend.dtos.UserDTO;

public interface AuthService {

    UserDTO registerUser(UserDTO userDTO);
}
