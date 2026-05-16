package com.example.trashformer.controller;

import com.example.trashformer.dto.request.PembayaranSampahRequest;
import com.example.trashformer.dto.request.UpdateStatusPembayaranRequest;
import com.example.trashformer.dto.response.PembayaranSampahResponse;
import com.example.trashformer.service.PembayaranSampahService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pembayaran")
public class PembayaranSampahController {

    private final PembayaranSampahService pembayaranSampahService;

    public PembayaranSampahController(PembayaranSampahService pembayaranSampahService) {
        this.pembayaranSampahService = pembayaranSampahService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PembayaranSampahResponse createPembayaran(@Valid @RequestBody PembayaranSampahRequest request) {
        return pembayaranSampahService.createPembayaran(request);
    }

    @GetMapping
    public List<PembayaranSampahResponse> getAllPembayaran() {
        return pembayaranSampahService.getAllPembayaran();
    }

    @GetMapping("/{id}")
    public PembayaranSampahResponse getPembayaranById(@PathVariable Long id) {
        return pembayaranSampahService.getPembayaranById(id);
    }

    @PatchMapping("/{id}/status")
    public PembayaranSampahResponse updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusPembayaranRequest request) {
        return pembayaranSampahService.updateStatus(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePembayaran(@PathVariable Long id) {
        pembayaranSampahService.deletePembayaran(id);
    }
}
