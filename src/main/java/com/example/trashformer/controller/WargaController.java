package com.example.trashformer.controller;

import com.example.trashformer.dto.request.WargaRequest;
import com.example.trashformer.dto.response.WargaResponse;
import com.example.trashformer.service.WargaService;
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
@RequestMapping("/api/warga")
public class WargaController {

    private final WargaService wargaService;

    public WargaController(WargaService wargaService) {
        this.wargaService = wargaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WargaResponse createWarga(@Valid @RequestBody WargaRequest request) {
        return wargaService.createWarga(request);
    }

    @GetMapping
    public List<WargaResponse> getAllWarga() {
        return wargaService.getAllWarga();
    }

    @GetMapping("/{id}")
    public WargaResponse getWargaById(@PathVariable Long id) {
        return wargaService.getWargaById(id);
    }
}
