package com.example.trashformer.service;

import com.example.trashformer.dto.request.PetugasRequest;
import com.example.trashformer.dto.response.PetugasResponse;

import java.util.List;

public interface PetugasService {

    PetugasResponse createPetugas(PetugasRequest request);

    List<PetugasResponse> getAllPetugas();

    PetugasResponse getPetugasById(Long id);
}
