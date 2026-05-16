package com.example.trashformer.dto.request;

import com.example.trashformer.enums.StatusPembayaran;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateStatusPembayaranRequest(
        @NotNull StatusPembayaran status,
        @Size(max = 500) String catatanVerifikasi
) {
}
