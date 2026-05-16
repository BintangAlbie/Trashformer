package com.example.trashformer.service;

import com.example.trashformer.dto.request.HasilLaporanRequest;
import com.example.trashformer.dto.request.LaporanSampahRequest;
import com.example.trashformer.dto.request.ReviewLaporanRequest;
import com.example.trashformer.dto.response.HasilLaporanResponse;
import com.example.trashformer.dto.response.LaporanSampahResponse;

import java.util.List;

public interface LaporanSampahService {

    LaporanSampahResponse createLaporan(LaporanSampahRequest request);

    List<LaporanSampahResponse> getAllLaporan();

    List<LaporanSampahResponse> getLaporanByWargaId(Long wargaId);

    LaporanSampahResponse getLaporanById(Long id);

    LaporanSampahResponse updateLaporan(Long id, LaporanSampahRequest request);

    LaporanSampahResponse reviewLaporan(Long id, ReviewLaporanRequest request);

    HasilLaporanResponse addHasilLaporan(Long laporanId, HasilLaporanRequest request);

    void deleteLaporan(Long id);
}
