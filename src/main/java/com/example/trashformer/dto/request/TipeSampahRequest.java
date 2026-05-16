package com.example.trashformer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TipeSampahRequest(
        @NotBlank @Size(max = 100) String namaTipe,
        @Size(max = 255) String deskripsi,
        @NotNull @PositiveOrZero BigDecimal hargaPerKg
) {
}
