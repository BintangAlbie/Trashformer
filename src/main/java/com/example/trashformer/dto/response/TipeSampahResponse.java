package com.example.trashformer.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TipeSampahResponse(
        Long id,
        String namaTipe,
        String deskripsi,
        BigDecimal hargaPerKg,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
