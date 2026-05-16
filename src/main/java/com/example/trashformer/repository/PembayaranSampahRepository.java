package com.example.trashformer.repository;

import com.example.trashformer.entity.PembayaranSampah;
import com.example.trashformer.enums.StatusPembayaran;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PembayaranSampahRepository extends JpaRepository<PembayaranSampah, Long> {

    List<PembayaranSampah> findAllByOrderByTanggalPembayaranDescIdDesc();

    List<PembayaranSampah> findTop5ByWargaIdOrderByTanggalPembayaranDescIdDesc(Long wargaId);

    List<PembayaranSampah> findByWargaIdOrderByTanggalPembayaranDescIdDesc(Long wargaId);

    long countByStatus(StatusPembayaran status);
}
