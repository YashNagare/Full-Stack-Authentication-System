package com.example.auth.auth_app_backend.services;

import com.example.auth.auth_app_backend.dtos.UserDTO;

import java.util.List;

public interface UserService {

//    create User
    UserDTO createUser(UserDTO userDTO);

//    get User by email
    UserDTO getUserByEmail(String email);

//    update User
    UserDTO updateUser(UserDTO userDTO, String userId);

//    delete User
    void deleteUser(String userId);

//    get User by id
    UserDTO getUserById(String id);

//    get all Users
    List<UserDTO> getAllUsers();
}
