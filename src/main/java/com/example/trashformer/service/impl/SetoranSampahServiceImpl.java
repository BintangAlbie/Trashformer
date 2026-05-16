package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.SetoranSampahRequest;
import com.example.trashformer.dto.request.UpdateStatusSetoranRequest;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.entity.Petugas;
import com.example.trashformer.entity.SetoranSampah;
import com.example.trashformer.entity.TipeSampah;
import com.example.trashformer.entity.Warga;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.SetoranSampahRepository;
import com.example.trashformer.repository.TipeSampahRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.SetoranSampahService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SetoranSampahServiceImpl implements SetoranSampahService {

    private final SetoranSampahRepository setoranSampahRepository;
    private final WargaRepository wargaRepository;
    private final PetugasRepository petugasRepository;
    private final TipeSampahRepository tipeSampahRepository;

    public SetoranSampahServiceImpl(
            SetoranSampahRepository setoranSampahRepository,
            WargaRepository wargaRepository,
            PetugasRepository petugasRepository,
            TipeSampahRepository tipeSampahRepository
    ) {
        this.setoranSampahRepository = setoranSampahRepository;
        this.wargaRepository = wargaRepository;
        this.petugasRepository = petugasRepository;
        this.tipeSampahRepository = tipeSampahRepository;
    }

    @Override
    public SetoranSampahResponse createSetoran(SetoranSampahRequest request) {
        Warga warga = wargaRepository.findById(request.wargaId())
                .orElseThrow(() -> new ResourceNotFoundException("Warga tidak ditemukan: " + request.wargaId()));
        Petugas petugas = petugasRepository.findById(request.petugasId())
                .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + request.petugasId()));
        TipeSampah tipeSampah = tipeSampahRepository.findById(request.tipeSampahId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipe sampah tidak ditemukan: " + request.tipeSampahId()));

        SetoranSampah setoranSampah = new SetoranSampah();
        setoranSampah.setWarga(warga);
        setoranSampah.setPetugas(petugas);
        setoranSampah.setTipeSampah(tipeSampah);
        setoranSampah.setBeratKg(request.beratKg());
        setoranSampah.setTanggalSetoran(request.tanggalSetoran());
        setoranSampah.setStatus(request.status());
        setoranSampah.setCatatan(request.catatan());

        return DtoMapper.toSetoranSampahResponse(setoranSampahRepository.save(setoranSampah));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SetoranSampahResponse> getAllSetoran() {
        return setoranSampahRepository.findAllByOrderByTanggalSetoranDescIdDesc().stream()
                .map(DtoMapper::toSetoranSampahResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SetoranSampahResponse getSetoranById(Long id) {
        SetoranSampah setoranSampah = setoranSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setoran tidak ditemukan: " + id));
        return DtoMapper.toSetoranSampahResponse(setoranSampah);
    }

    @Override
    public SetoranSampahResponse updateStatus(Long id, UpdateStatusSetoranRequest request) {
        SetoranSampah setoranSampah = setoranSampahRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setoran tidak ditemukan: " + id));

        setoranSampah.setStatus(request.status());
        if (request.catatan() != null && !request.catatan().isBlank()) {
            setoranSampah.setCatatan(request.catatan());
        }

        return DtoMapper.toSetoranSampahResponse(setoranSampahRepository.save(setoranSampah));
    }
}
