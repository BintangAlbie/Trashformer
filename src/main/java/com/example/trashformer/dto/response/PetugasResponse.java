package com.example.trashformer.dto.response;

import java.time.LocalDateTime;

public record PetugasResponse(
        Long id,
        Long userId,
        String nama,
        String email,
        String nomorPetugas,
        String alamat,
        String noHp,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
