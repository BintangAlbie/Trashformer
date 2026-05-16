package com.example.trashformer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WargaRequest(
        @NotBlank @Size(max = 120) String nama,
        @NotBlank @Email @Size(max = 120) String email,
        @NotBlank @Size(min = 8, max = 255) String password,
        @NotBlank @Size(max = 32) String nik,
        @NotBlank @Size(max = 255) String alamat,
        @NotBlank @Size(max = 30) String noHp,
        @NotBlank @Size(max = 10) String rt,
        @NotBlank @Size(max = 10) String rw
) {
}
