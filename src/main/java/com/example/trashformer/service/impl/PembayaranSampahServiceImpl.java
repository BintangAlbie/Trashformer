package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.PembayaranSampahRequest;
import com.example.trashformer.dto.request.UpdateStatusPembayaranRequest;
import com.example.trashformer.dto.response.PembayaranSampahResponse;
import com.example.trashformer.entity.PembayaranSampah;
import com.example.trashformer.entity.Warga;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.PembayaranSampahRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.PembayaranSampahService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PembayaranSampahServiceImpl implements PembayaranSampahService {

    private final PembayaranSampahRepository pembayaranSampahRepository;
    private final WargaRepository wargaRepository;

    public PembayaranSampahServiceImpl(
            PembayaranSampahRepository pembayaranSampahRepository,
            WargaRepository wargaRepository
    ) {
        this.pembayaranSampahRepository = pembayaranSampahRepository;
        this.wargaRepository = wargaRepository;
    }

    @Override
    public PembayaranSampahResponse createPembayaran(PembayaranSampahRequest request) {
        Warga warga = wargaRepository.findById(request.wargaId())
                .orElseThrow(() -> new ResourceNotFoundException("Warga tidak ditemukan: " + request.wargaId()));

        PembayaranSampah pembayaranSampah = new PembayaranSampah();
        pembayaranSampah.setWarga(warga);
        pembayaranSampah.setPeriodeTagihan(request.periodeTagihan());
        pembayaranSampah.setNominal(request.nominal());
        pembayaranSampah.setTanggalPembayaran(request.tanggalPembayaran());
        pembayaranSampah.setMetodePembayaran(request.metodePembayaran());
        pembayaranSampah.setStatus(request.status());
        pembayaranSampah.setKeterangan(request.keterangan());
        pembayaranSampah.setCatatanVerifikasi(request.catatanVerifikasi());
        pembayaranSampah.setBuktiPembayaranUrl(request.buktiPembayaranUrl());

        return DtoMapper.toPembayaranSampahResponse(pembayaranSampahRepository.save(pembayaranSampah));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PembayaranSampahResponse> getAllPembayaran() {
        return pembayaranSampahRepository.findAllByOrderByTanggalPembayaranDescIdDesc().stream()
                .map(DtoMapper::toPembayaranSampahResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PembayaranSampahResponse> getPembayaranByWargaId(Long wargaId) {
        return pembayaranSampahRepository.findByWargaIdOrderByTanggalPembayaranDescIdDesc(wargaId).stream()
                .map(DtoMapper::toPembayaranSampahResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PembayaranSampahResponse getPembayaranById(Long id) {
        PembayaranSampah pembayaranSampah = pembayaranSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pembayaran tidak ditemukan: " + id));
        return DtoMapper.toPembayaranSampahResponse(pembayaranSampah);
    }

    @Override
    public PembayaranSampahResponse updateStatus(Long id, UpdateStatusPembayaranRequest request) {
        PembayaranSampah pembayaranSampah = pembayaranSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pembayaran tidak ditemukan: " + id));

        pembayaranSampah.setStatus(request.status());
        pembayaranSampah.setCatatanVerifikasi(request.catatanVerifikasi());

        return DtoMapper.toPembayaranSampahResponse(pembayaranSampahRepository.save(pembayaranSampah));
    }

    @Override
    public void deletePembayaran(Long id) {
        PembayaranSampah pembayaranSampah = pembayaranSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pembayaran tidak ditemukan: " + id));
        pembayaranSampahRepository.delete(pembayaranSampah);
    }
}
