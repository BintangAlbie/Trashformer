package com.example.trashformer.dto.request;

import com.example.trashformer.enums.StatusSetoran;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateStatusSetoranRequest(
        @NotNull StatusSetoran status,
        @Size(max = 500) String catatan
) {
}
