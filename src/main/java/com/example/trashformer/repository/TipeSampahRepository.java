package com.example.trashformer.repository;

import com.example.trashformer.entity.TipeSampah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipeSampahRepository extends JpaRepository<TipeSampah, Long> {

    boolean existsByNamaTipeIgnoreCase(String namaTipe);

    Optional<TipeSampah> findByNamaTipeIgnoreCase(String namaTipe);

    List<TipeSampah> findAllByOrderByNamaTipeAsc();
}
