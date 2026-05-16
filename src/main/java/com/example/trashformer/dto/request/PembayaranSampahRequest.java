package com.example.trashformer.dto.request;

import com.example.trashformer.enums.StatusPembayaran;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PembayaranSampahRequest(
        @NotNull Long wargaId,
        @NotBlank @Size(max = 100) String periodeTagihan,
        @NotNull @Positive BigDecimal nominal,
        @NotNull LocalDate tanggalPembayaran,
        @NotBlank @Size(max = 50) String metodePembayaran,
        @NotNull StatusPembayaran status,
        @Size(max = 500) String keterangan,
        @Size(max = 500) String catatanVerifikasi,
        @Size(max = 255) String buktiPembayaranUrl
) {
}
