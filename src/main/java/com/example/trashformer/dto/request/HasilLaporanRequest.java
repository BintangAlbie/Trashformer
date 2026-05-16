package com.example.trashformer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record HasilLaporanRequest(
        @NotNull Long petugasId,
        @NotBlank String keteranganHasil,
        @NotNull LocalDate tanggalSelesai,
        @Size(max = 255) String filePdfUrl
) {
}
