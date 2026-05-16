package com.example.trashformer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminUserRequest(
        @NotBlank @Size(max = 120) String nama,
        @NotBlank @Email @Size(max = 120) String email,
        @NotBlank @Size(min = 8, max = 255) String password
) {
}
