package com.example.trashformer.repository;

import com.example.trashformer.entity.LaporanSampah;
import com.example.trashformer.enums.StatusLaporan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaporanSampahRepository extends JpaRepository<LaporanSampah, Long> {

    List<LaporanSampah> findAllByOrderByTanggalLaporanDescIdDesc();

    List<LaporanSampah> findTop5ByWargaIdOrderByTanggalLaporanDescIdDesc(Long wargaId);

    List<LaporanSampah> findTop5ByPetugasIdOrderByTanggalLaporanDescIdDesc(Long petugasId);

    List<LaporanSampah> findByWargaIdOrderByTanggalLaporanDescIdDesc(Long wargaId);

    long countByWargaId(Long wargaId);

    long countByStatusLaporan(StatusLaporan statusLaporan);

    long countByPetugasIdAndStatusLaporan(Long petugasId, StatusLaporan statusLaporan);
}
