package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.HasilLaporanRequest;
import com.example.trashformer.dto.request.LaporanSampahRequest;
import com.example.trashformer.dto.request.ReviewLaporanRequest;
import com.example.trashformer.dto.response.HasilLaporanResponse;
import com.example.trashformer.dto.response.LaporanSampahResponse;
import com.example.trashformer.entity.HasilLaporan;
import com.example.trashformer.entity.LaporanSampah;
import com.example.trashformer.entity.Petugas;
import com.example.trashformer.entity.TipeSampah;
import com.example.trashformer.entity.Warga;
import com.example.trashformer.enums.StatusLaporan;
import com.example.trashformer.exception.BusinessException;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.HasilLaporanRepository;
import com.example.trashformer.repository.LaporanSampahRepository;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.TipeSampahRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.LaporanSampahService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LaporanSampahServiceImpl implements LaporanSampahService {

    private final LaporanSampahRepository laporanSampahRepository;
    private final HasilLaporanRepository hasilLaporanRepository;
    private final WargaRepository wargaRepository;
    private final PetugasRepository petugasRepository;
    private final TipeSampahRepository tipeSampahRepository;

    public LaporanSampahServiceImpl(
            LaporanSampahRepository laporanSampahRepository,
            HasilLaporanRepository hasilLaporanRepository,
            WargaRepository wargaRepository,
            PetugasRepository petugasRepository,
            TipeSampahRepository tipeSampahRepository
    ) {
        this.laporanSampahRepository = laporanSampahRepository;
        this.hasilLaporanRepository = hasilLaporanRepository;
        this.wargaRepository = wargaRepository;
        this.petugasRepository = petugasRepository;
        this.tipeSampahRepository = tipeSampahRepository;
    }

    @Override
    public LaporanSampahResponse createLaporan(LaporanSampahRequest request) {
        Warga warga = wargaRepository.findById(request.wargaId())
                .orElseThrow(() -> new ResourceNotFoundException("Warga tidak ditemukan: " + request.wargaId()));

        LaporanSampah laporanSampah = new LaporanSampah();
        laporanSampah.setWarga(warga);
        laporanSampah.setJudulLaporan(request.judulLaporan());
        laporanSampah.setDeskripsi(request.deskripsi());
        laporanSampah.setLokasi(request.lokasi());
        laporanSampah.setTanggalLaporan(request.tanggalLaporan());
        laporanSampah.setStatusLaporan(request.statusLaporan());
        laporanSampah.setFotoUrl(request.fotoUrl());

        if (request.petugasId() != null) {
            Petugas petugas = petugasRepository.findById(request.petugasId())
                    .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + request.petugasId()));
            laporanSampah.setPetugas(petugas);
        }
        if (request.tipeSampahId() != null) {
            TipeSampah tipeSampah = tipeSampahRepository.findById(request.tipeSampahId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipe sampah tidak ditemukan: " + request.tipeSampahId()));
            laporanSampah.setTipeSampah(tipeSampah);
        }

        return DtoMapper.toLaporanSampahResponse(laporanSampahRepository.save(laporanSampah));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LaporanSampahResponse> getAllLaporan() {
        return laporanSampahRepository.findAllByOrderByTanggalLaporanDescIdDesc().stream()
                .map(DtoMapper::toLaporanSampahResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LaporanSampahResponse> getLaporanByWargaId(Long wargaId) {
        return laporanSampahRepository.findByWargaIdOrderByTanggalLaporanDescIdDesc(wargaId).stream()
                .map(DtoMapper::toLaporanSampahResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LaporanSampahResponse getLaporanById(Long id) {
        LaporanSampah laporanSampah = laporanSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laporan sampah tidak ditemukan: " + id));
        return DtoMapper.toLaporanSampahResponse(laporanSampah);
    }

    @Override
    public LaporanSampahResponse updateLaporan(Long id, LaporanSampahRequest request) {
        LaporanSampah laporanSampah = laporanSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laporan sampah tidak ditemukan: " + id));

        Warga warga = wargaRepository.findById(request.wargaId())
                .orElseThrow(() -> new ResourceNotFoundException("Warga tidak ditemukan: " + request.wargaId()));

        laporanSampah.setWarga(warga);
        laporanSampah.setJudulLaporan(request.judulLaporan());
        laporanSampah.setDeskripsi(request.deskripsi());
        laporanSampah.setLokasi(request.lokasi());
        laporanSampah.setTanggalLaporan(request.tanggalLaporan());
        laporanSampah.setStatusLaporan(request.statusLaporan());
        laporanSampah.setFotoUrl(request.fotoUrl());

        if (request.petugasId() != null) {
            Petugas petugas = petugasRepository.findById(request.petugasId())
                    .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + request.petugasId()));
            laporanSampah.setPetugas(petugas);
        } else {
            laporanSampah.setPetugas(null);
        }

        if (request.tipeSampahId() != null) {
            TipeSampah tipeSampah = tipeSampahRepository.findById(request.tipeSampahId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipe sampah tidak ditemukan: " + request.tipeSampahId()));
            laporanSampah.setTipeSampah(tipeSampah);
        } else {
            laporanSampah.setTipeSampah(null);
        }

        return DtoMapper.toLaporanSampahResponse(laporanSampahRepository.save(laporanSampah));
    }

    @Override
    public LaporanSampahResponse reviewLaporan(Long id, ReviewLaporanRequest request) {
        if (request.statusLaporan() == StatusLaporan.BARU) {
            throw new BusinessException("Status review tidak boleh kembali ke BARU.");
        }

        LaporanSampah laporanSampah = laporanSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laporan sampah tidak ditemukan: " + id));

        Petugas petugas = petugasRepository.findById(request.petugasId())
                .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + request.petugasId()));

        laporanSampah.setPetugas(petugas);
        laporanSampah.setStatusLaporan(request.statusLaporan());
        laporanSampahRepository.save(laporanSampah);

        if (request.statusLaporan() == StatusLaporan.SELESAI || request.statusLaporan() == StatusLaporan.DITOLAK) {
            upsertHasilLaporan(laporanSampah, petugas, request.keteranganHasil());
        }

        return DtoMapper.toLaporanSampahResponse(laporanSampah);
    }

    @Override
    public HasilLaporanResponse addHasilLaporan(Long laporanId, HasilLaporanRequest request) {
        LaporanSampah laporanSampah = laporanSampahRepository.findById(laporanId)
                .orElseThrow(() -> new ResourceNotFoundException("Laporan sampah tidak ditemukan: " + laporanId));

        if (hasilLaporanRepository.findByLaporanSampahId(laporanId).isPresent()) {
            throw new BusinessException("Hasil laporan untuk laporan ini sudah ada.");
        }

        Petugas petugas = petugasRepository.findById(request.petugasId())
                .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + request.petugasId()));

        HasilLaporan hasilLaporan = new HasilLaporan();
        hasilLaporan.setLaporanSampah(laporanSampah);
        hasilLaporan.setPetugas(petugas);
        hasilLaporan.setKeteranganHasil(request.keteranganHasil());
        hasilLaporan.setTanggalSelesai(request.tanggalSelesai());
        hasilLaporan.setFilePdfUrl(request.filePdfUrl());

        laporanSampah.setPetugas(petugas);
        laporanSampah.setStatusLaporan(StatusLaporan.SELESAI);
        laporanSampahRepository.save(laporanSampah);

        return DtoMapper.toHasilLaporanResponse(hasilLaporanRepository.save(hasilLaporan));
    }

    @Override
    public void deleteLaporan(Long id) {
        LaporanSampah laporanSampah = laporanSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laporan sampah tidak ditemukan: " + id));
        hasilLaporanRepository.findByLaporanSampahId(id)
                .ifPresent(hasilLaporanRepository::delete);
        laporanSampahRepository.delete(laporanSampah);
    }

    private void upsertHasilLaporan(LaporanSampah laporanSampah, Petugas petugas, String keteranganHasil) {
        HasilLaporan hasilLaporan = hasilLaporanRepository.findByLaporanSampahId(laporanSampah.getId())
                .orElseGet(HasilLaporan::new);

        hasilLaporan.setLaporanSampah(laporanSampah);
        hasilLaporan.setPetugas(petugas);
        hasilLaporan.setKeteranganHasil(keteranganHasil);
        hasilLaporan.setTanggalSelesai(java.time.LocalDate.now());
        laporanSampah.setHasilLaporan(hasilLaporan);
        hasilLaporanRepository.save(hasilLaporan);
    }
}
