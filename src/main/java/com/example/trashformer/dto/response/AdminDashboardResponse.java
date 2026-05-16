package com.example.trashformer.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record AdminDashboardResponse(
        Long totalUser,
        Long totalAdmin,
        Long totalPetugas,
        Long totalWarga,
        Long totalSetoranHariIni,
        BigDecimal totalBeratHariIni,
        Long totalSetoranBulanIni,
        BigDecimal totalBeratBulanIni,
        Long totalSetoranTahunIni,
        BigDecimal totalBeratTahunIni,
        Long setoranMenunggu,
        Long laporanBaru,
        Long pembayaranMenunggu,
        List<CategoryWasteSummaryResponse> ringkasanPerTipe,
        List<CategoryWasteSummaryResponse> ringkasanPerPetugas,
        List<RoleWasteSummaryResponse> ringkasanPerRole
) {
}
