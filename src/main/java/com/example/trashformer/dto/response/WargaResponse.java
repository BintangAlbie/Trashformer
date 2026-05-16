package com.example.trashformer.dto.response;

import java.time.LocalDateTime;

public record WargaResponse(
        Long id,
        Long userId,
        String nama,
        String email,
        String nik,
        String alamat,
        String noHp,
        String rt,
        String rw,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
