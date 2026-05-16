package com.example.trashformer.dto.response;

import java.math.BigDecimal;

public record CategoryWasteSummaryResponse(
        String kategori,
        BigDecimal totalBeratKg,
        Long jumlahSetoran
) {
}
