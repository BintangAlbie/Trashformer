package com.example.trashformer.dto.request;

import com.example.trashformer.enums.StatusSetoran;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SetoranSampahRequest(
        @NotNull Long wargaId,
        @NotNull Long petugasId,
        @NotNull Long tipeSampahId,
        @NotNull @Positive BigDecimal beratKg,
        @NotNull LocalDate tanggalSetoran,
        @NotNull StatusSetoran status,
        @Size(max = 500) String catatan
) {
}
