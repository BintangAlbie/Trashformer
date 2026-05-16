package com.example.trashformer.dto.response;

import com.example.trashformer.enums.Role;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String nama,
        String email,
        Role role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
