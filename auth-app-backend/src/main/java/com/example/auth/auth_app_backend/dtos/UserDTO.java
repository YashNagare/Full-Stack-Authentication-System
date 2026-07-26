package com.example.auth.auth_app_backend.dtos;

import com.example.auth.auth_app_backend.entities.Provider;
import com.example.auth.auth_app_backend.entities.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private UUID uuid;
    private String email;
    private String username;
    private String password;
    private String profilePic;
    private boolean enabled = true;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    private Provider provider = Provider.LOCAL;
    private Set<RoleDTO> roles = new HashSet<>();

}
