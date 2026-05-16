package com.example.trashformer.controller;

import com.example.trashformer.dto.request.SetoranSampahRequest;
import com.example.trashformer.dto.request.UpdateStatusSetoranRequest;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.service.SetoranSampahService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/setoran")
public class SetoranSampahController {

    private final SetoranSampahService setoranSampahService;

    public SetoranSampahController(SetoranSampahService setoranSampahService) {
        this.setoranSampahService = setoranSampahService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SetoranSampahResponse createSetoran(@Valid @RequestBody SetoranSampahRequest request) {
        return setoranSampahService.createSetoran(request);
    }

    @GetMapping
    public List<SetoranSampahResponse> getAllSetoran() {
        return setoranSampahService.getAllSetoran();
    }

    @GetMapping("/{id}")
    public SetoranSampahResponse getSetoranById(@PathVariable Long id) {
        return setoranSampahService.getSetoranById(id);
    }

    @PatchMapping("/{id}/status")
    public SetoranSampahResponse updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusSetoranRequest request) {
        return setoranSampahService.updateStatus(id, request);
    }
}
