package com.example.trashformer.service;

import com.example.trashformer.dto.request.PembayaranSampahRequest;
import com.example.trashformer.dto.request.UpdateStatusPembayaranRequest;
import com.example.trashformer.dto.response.PembayaranSampahResponse;

import java.util.List;

public interface PembayaranSampahService {

    PembayaranSampahResponse createPembayaran(PembayaranSampahRequest request);

    List<PembayaranSampahResponse> getAllPembayaran();

    List<PembayaranSampahResponse> getPembayaranByWargaId(Long wargaId);

    PembayaranSampahResponse getPembayaranById(Long id);

    PembayaranSampahResponse updateStatus(Long id, UpdateStatusPembayaranRequest request);

    void deletePembayaran(Long id);
}
