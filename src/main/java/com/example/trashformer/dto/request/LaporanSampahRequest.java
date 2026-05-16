package com.example.trashformer.dto.request;

import com.example.trashformer.enums.StatusLaporan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LaporanSampahRequest(
        @NotNull Long wargaId,
        Long petugasId,
        Long tipeSampahId,
        @NotBlank @Size(max = 150) String judulLaporan,
        @NotBlank String deskripsi,
        @NotBlank @Size(max = 255) String lokasi,
        @NotNull LocalDate tanggalLaporan,
        @NotNull StatusLaporan statusLaporan,
        @Size(max = 255) String fotoUrl
) {
}
