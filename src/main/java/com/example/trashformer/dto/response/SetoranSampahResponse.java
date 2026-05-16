package com.example.trashformer.dto.response;

import com.example.trashformer.enums.StatusSetoran;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record SetoranSampahResponse(
        Long id,
        Long wargaId,
        String namaWarga,
        Long petugasId,
        String namaPetugas,
        Long tipeSampahId,
        String namaTipeSampah,
        BigDecimal beratKg,
        LocalDate tanggalSetoran,
        StatusSetoran status,
        String catatan,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
