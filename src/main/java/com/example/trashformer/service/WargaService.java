package com.example.trashformer.service;

import com.example.trashformer.dto.request.WargaRequest;
import com.example.trashformer.dto.response.WargaResponse;

import java.util.List;

public interface WargaService {

    WargaResponse createWarga(WargaRequest request);

    List<WargaResponse> getAllWarga();

    WargaResponse getWargaById(Long id);
}
