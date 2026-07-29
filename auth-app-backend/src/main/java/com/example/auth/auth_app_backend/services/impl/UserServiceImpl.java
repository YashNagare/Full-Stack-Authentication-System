package com.example.auth.auth_app_backend.services.impl;

import com.example.auth.auth_app_backend.dtos.UserDTO;
import com.example.auth.auth_app_backend.entities.Provider;
import com.example.auth.auth_app_backend.entities.User;
import com.example.auth.auth_app_backend.exceptions.ResourceNotFoundException;
import com.example.auth.auth_app_backend.helpers.UserHelper;
import com.example.auth.auth_app_backend.repositories.UserRepository;
import com.example.auth.auth_app_backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {

        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("User with email " + userDTO.getEmail() + " already exists");
        }

        User user = modelMapper.map(userDTO, User.class);
        user.setProvider(userDTO.getProvider() != null ? userDTO.getProvider() : Provider.LOCAL);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String userId) {
        UUID uuid = UserHelper.parseUUID(userId);
        User existingUser = userRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        if (userDTO.getUsername() != null) existingUser.setUsername(userDTO.getUsername());
        if (userDTO.getProfilePic() != null) existingUser.setProfilePic(userDTO.getProfilePic());
        if (userDTO.getProvider() != null) existingUser.setProvider(userDTO.getProvider());
        if (userDTO.getEmail() != null) existingUser.setEmail(userDTO.getEmail());
        existingUser.setEnabled(userDTO.isEnabled());
        existingUser.setUpdatedAt(Instant.now());

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID uuid = UserHelper.parseUUID(userId);
        User user = userRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(UserHelper.parseUUID(id)).orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }
}
