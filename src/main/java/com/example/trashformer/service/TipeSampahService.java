package com.example.trashformer.service;

import com.example.trashformer.dto.request.TipeSampahRequest;
import com.example.trashformer.dto.response.TipeSampahResponse;

import java.util.List;

public interface TipeSampahService {

    TipeSampahResponse createTipeSampah(TipeSampahRequest request);

    List<TipeSampahResponse> getAllTipeSampah();

    TipeSampahResponse getTipeSampahById(Long id);
}
