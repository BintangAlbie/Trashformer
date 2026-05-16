package com.example.trashformer.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record WargaDashboardResponse(
        Long wargaId,
        String namaWarga,
        Long totalSetoran,
        BigDecimal totalBeratKg,
        Long setoranDiterima,
        Long setoranMenunggu,
        Long totalLaporan,
        Long totalPembayaran,
        Long pembayaranMenunggu,
        Long pembayaranLunas,
        BigDecimal totalNominalPembayaran,
        List<SetoranSampahResponse> setoranTerbaru,
        List<LaporanSampahResponse> laporanTerbaru,
        List<PembayaranSampahResponse> pembayaranTerbaru
) {
}
