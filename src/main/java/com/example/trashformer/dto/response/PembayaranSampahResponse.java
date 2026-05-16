package com.example.trashformer.dto.response;

import com.example.trashformer.enums.StatusPembayaran;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PembayaranSampahResponse(
        Long id,
        Long wargaId,
        String namaWarga,
        String periodeTagihan,
        BigDecimal nominal,
        LocalDate tanggalPembayaran,
        String metodePembayaran,
        StatusPembayaran status,
        String keterangan,
        String catatanVerifikasi,
        String buktiPembayaranUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
