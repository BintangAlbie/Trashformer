package com.example.trashformer.dto.response;

import com.example.trashformer.enums.StatusLaporan;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LaporanSampahResponse(
        Long id,
        Long wargaId,
        String namaWarga,
        Long petugasId,
        String namaPetugas,
        Long tipeSampahId,
        String namaTipeSampah,
        String judulLaporan,
        String deskripsi,
        String lokasi,
        LocalDate tanggalLaporan,
        StatusLaporan statusLaporan,
        String fotoUrl,
        HasilLaporanResponse hasilLaporan,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
