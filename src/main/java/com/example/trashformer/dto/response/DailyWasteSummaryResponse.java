package com.example.trashformer.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyWasteSummaryResponse(
        LocalDate tanggal,
        BigDecimal totalBeratKg,
        Long jumlahSetoran
) {
}
