package com.example.trashformer.dto.response;

import java.math.BigDecimal;

public record MonthlyWasteSummaryResponse(
        Integer tahun,
        Integer bulan,
        BigDecimal totalBeratKg,
        Long jumlahSetoran
) {
}
