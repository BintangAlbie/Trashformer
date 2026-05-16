package com.example.trashformer.repository;

import com.example.trashformer.entity.Petugas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetugasRepository extends JpaRepository<Petugas, Long> {

    boolean existsByNomorPetugas(String nomorPetugas);

    Optional<Petugas> findByUserId(Long userId);

    List<Petugas> findAllByOrderByCreatedAtDesc();
}
