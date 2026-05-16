package com.example.trashformer.repository;

import com.example.trashformer.dto.response.DailyWasteSummaryResponse;
import com.example.trashformer.dto.response.MonthlyWasteSummaryResponse;
import com.example.trashformer.entity.SetoranSampah;
import com.example.trashformer.enums.StatusSetoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SetoranSampahRepository extends JpaRepository<SetoranSampah, Long>, JpaSpecificationExecutor<SetoranSampah> {

    List<SetoranSampah> findAllByOrderByTanggalSetoranDescIdDesc();

    List<SetoranSampah> findTop5ByWargaIdOrderByTanggalSetoranDescIdDesc(Long wargaId);

    List<SetoranSampah> findTop5ByPetugasIdOrderByTanggalSetoranDescIdDesc(Long petugasId);

    List<SetoranSampah> findByWargaIdOrderByTanggalSetoranDescIdDesc(Long wargaId);

    List<SetoranSampah> findByPetugasIdOrderByTanggalSetoranDescIdDesc(Long petugasId);

    long countByStatus(StatusSetoran status);

    long countByTanggalSetoran(LocalDate tanggalSetoran);

    @Query("""
        select new com.example.trashformer.dto.response.DailyWasteSummaryResponse(
            s.tanggalSetoran,
            sum(s.beratKg),
            count(s.id)
        )
        from SetoranSampah s
        where s.tanggalSetoran between :startDate and :endDate
        group by s.tanggalSetoran
        order by s.tanggalSetoran
        """)
    List<DailyWasteSummaryResponse> findDailySummary(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("""
        select new com.example.trashformer.dto.response.MonthlyWasteSummaryResponse(
            year(s.tanggalSetoran),
            month(s.tanggalSetoran),
            sum(s.beratKg),
            count(s.id)
        )
        from SetoranSampah s
        where s.tanggalSetoran between :startDate and :endDate
        group by year(s.tanggalSetoran), month(s.tanggalSetoran)
        order by year(s.tanggalSetoran), month(s.tanggalSetoran)
        """)
    List<MonthlyWasteSummaryResponse> findMonthlySummary(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
