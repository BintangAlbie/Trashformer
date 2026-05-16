package com.example.trashformer.service;

import com.example.trashformer.dto.request.SetoranSampahRequest;
import com.example.trashformer.dto.request.UpdateStatusSetoranRequest;
import com.example.trashformer.dto.response.SetoranSampahResponse;

import java.util.List;

public interface SetoranSampahService {

    SetoranSampahResponse createSetoran(SetoranSampahRequest request);

    List<SetoranSampahResponse> getAllSetoran();

    SetoranSampahResponse getSetoranById(Long id);

    SetoranSampahResponse updateStatus(Long id, UpdateStatusSetoranRequest request);
}
