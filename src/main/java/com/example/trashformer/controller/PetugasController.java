package com.example.trashformer.controller;

import com.example.trashformer.dto.request.PetugasRequest;
import com.example.trashformer.dto.response.PetugasResponse;
import com.example.trashformer.service.PetugasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/petugas")
public class PetugasController {

    private final PetugasService petugasService;

    public PetugasController(PetugasService petugasService) {
        this.petugasService = petugasService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetugasResponse createPetugas(@Valid @RequestBody PetugasRequest request) {
        return petugasService.createPetugas(request);
    }

    @GetMapping
    public List<PetugasResponse> getAllPetugas() {
        return petugasService.getAllPetugas();
    }

    @GetMapping("/{id}")
    public PetugasResponse getPetugasById(@PathVariable Long id) {
        return petugasService.getPetugasById(id);
    }
}
