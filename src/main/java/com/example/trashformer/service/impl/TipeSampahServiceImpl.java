package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.TipeSampahRequest;
import com.example.trashformer.dto.response.TipeSampahResponse;
import com.example.trashformer.entity.TipeSampah;
import com.example.trashformer.exception.BusinessException;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.TipeSampahRepository;
import com.example.trashformer.service.TipeSampahService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipeSampahServiceImpl implements TipeSampahService {

    private final TipeSampahRepository tipeSampahRepository;

    public TipeSampahServiceImpl(TipeSampahRepository tipeSampahRepository) {
        this.tipeSampahRepository = tipeSampahRepository;
    }

    @Override
    public TipeSampahResponse createTipeSampah(TipeSampahRequest request) {
        if (tipeSampahRepository.existsByNamaTipeIgnoreCase(request.namaTipe())) {
            throw new BusinessException("Tipe sampah sudah ada.");
        }

        TipeSampah tipeSampah = new TipeSampah();
        tipeSampah.setNamaTipe(request.namaTipe());
        tipeSampah.setDeskripsi(request.deskripsi());
        tipeSampah.setHargaPerKg(request.hargaPerKg());

        return DtoMapper.toTipeSampahResponse(tipeSampahRepository.save(tipeSampah));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipeSampahResponse> getAllTipeSampah() {
        return tipeSampahRepository.findAllByOrderByNamaTipeAsc().stream()
                .map(DtoMapper::toTipeSampahResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TipeSampahResponse getTipeSampahById(Long id) {
        TipeSampah tipeSampah = tipeSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipe sampah tidak ditemukan: " + id));
        return DtoMapper.toTipeSampahResponse(tipeSampah);
    }
}
