package com.example.trashformer.repository;

import com.example.trashformer.entity.Warga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WargaRepository extends JpaRepository<Warga, Long> {

    boolean existsByNik(String nik);

    Optional<Warga> findByUserId(Long userId);

    List<Warga> findAllByOrderByCreatedAtDesc();
}
