package com.example.trashformer.dto.request;

import com.example.trashformer.enums.StatusLaporan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewLaporanRequest(
        @NotNull Long petugasId,
        @NotNull StatusLaporan statusLaporan,
        @NotBlank String keteranganHasil
) {
}
