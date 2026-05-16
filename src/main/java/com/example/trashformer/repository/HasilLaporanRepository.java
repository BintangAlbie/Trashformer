package com.example.trashformer.repository;

import com.example.trashformer.entity.HasilLaporan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HasilLaporanRepository extends JpaRepository<HasilLaporan, Long> {

    Optional<HasilLaporan> findByLaporanSampahId(Long laporanSampahId);
}
