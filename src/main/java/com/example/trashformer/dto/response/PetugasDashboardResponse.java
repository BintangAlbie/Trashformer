package com.example.trashformer.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record PetugasDashboardResponse(
        Long petugasId,
        String namaPetugas,
        Long totalSetoranDitangani,
        BigDecimal totalBeratDitangani,
        Long totalVerifikasiHariIni,
        BigDecimal totalBeratHariIni,
        Long totalLaporanDiproses,
        Long totalLaporanBaru,
        List<SetoranSampahResponse> setoranTerbaru,
        List<LaporanSampahResponse> laporanTerbaru
) {
}
