package com.example.trashformer.controller;

import com.example.trashformer.dto.request.TipeSampahRequest;
import com.example.trashformer.dto.response.TipeSampahResponse;
import com.example.trashformer.service.TipeSampahService;
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
@RequestMapping("/api/tipe-sampah")
public class TipeSampahController {

    private final TipeSampahService tipeSampahService;

    public TipeSampahController(TipeSampahService tipeSampahService) {
        this.tipeSampahService = tipeSampahService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipeSampahResponse createTipeSampah(@Valid @RequestBody TipeSampahRequest request) {
        return tipeSampahService.createTipeSampah(request);
    }

    @GetMapping
    public List<TipeSampahResponse> getAllTipeSampah() {
        return tipeSampahService.getAllTipeSampah();
    }

    @GetMapping("/{id}")
    public TipeSampahResponse getTipeSampahById(@PathVariable Long id) {
        return tipeSampahService.getTipeSampahById(id);
    }
}
