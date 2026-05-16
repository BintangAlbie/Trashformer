package com.example.trashformer.controller;

import com.example.trashformer.dto.request.HasilLaporanRequest;
import com.example.trashformer.dto.request.LaporanSampahRequest;
import com.example.trashformer.dto.request.ReviewLaporanRequest;
import com.example.trashformer.dto.response.HasilLaporanResponse;
import com.example.trashformer.dto.response.LaporanSampahResponse;
import com.example.trashformer.service.LaporanSampahService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/laporan-sampah")
public class LaporanSampahController {

    private final LaporanSampahService laporanSampahService;

    public LaporanSampahController(LaporanSampahService laporanSampahService) {
        this.laporanSampahService = laporanSampahService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LaporanSampahResponse createLaporan(@Valid @RequestBody LaporanSampahRequest request) {
        return laporanSampahService.createLaporan(request);
    }

    @GetMapping
    public List<LaporanSampahResponse> getAllLaporan() {
        return laporanSampahService.getAllLaporan();
    }

    @GetMapping("/{id}")
    public LaporanSampahResponse getLaporanById(@PathVariable Long id) {
        return laporanSampahService.getLaporanById(id);
    }

    @PutMapping("/{id}")
    public LaporanSampahResponse updateLaporan(@PathVariable Long id, @Valid @RequestBody LaporanSampahRequest request) {
        return laporanSampahService.updateLaporan(id, request);
    }

    @PatchMapping("/{id}/review")
    public LaporanSampahResponse reviewLaporan(@PathVariable Long id, @Valid @RequestBody ReviewLaporanRequest request) {
        return laporanSampahService.reviewLaporan(id, request);
    }

    @PostMapping("/{laporanId}/hasil")
    @ResponseStatus(HttpStatus.CREATED)
    public HasilLaporanResponse addHasilLaporan(@PathVariable Long laporanId, @Valid @RequestBody HasilLaporanRequest request) {
        return laporanSampahService.addHasilLaporan(laporanId, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLaporan(@PathVariable Long id) {
        laporanSampahService.deleteLaporan(id);
    }
}
