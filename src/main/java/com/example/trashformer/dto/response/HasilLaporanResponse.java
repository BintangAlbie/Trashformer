package com.example.trashformer.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record HasilLaporanResponse(
        Long id,
        Long laporanSampahId,
        Long petugasId,
        String namaPetugas,
        String keteranganHasil,
        LocalDate tanggalSelesai,
        String filePdfUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
