package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.AdminDashboardResponse;
import com.example.trashformer.dto.response.CategoryWasteSummaryResponse;
import com.example.trashformer.dto.response.LaporanSampahResponse;
import com.example.trashformer.dto.response.PembayaranSampahResponse;
import com.example.trashformer.dto.response.PetugasDashboardResponse;
import com.example.trashformer.dto.response.RoleWasteSummaryResponse;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.dto.response.WargaDashboardResponse;
import com.example.trashformer.entity.LaporanSampah;
import com.example.trashformer.entity.PembayaranSampah;
import com.example.trashformer.entity.Petugas;
import com.example.trashformer.entity.SetoranSampah;
import com.example.trashformer.entity.Warga;
import com.example.trashformer.enums.Role;
import com.example.trashformer.enums.StatusLaporan;
import com.example.trashformer.enums.StatusPembayaran;
import com.example.trashformer.enums.StatusSetoran;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.LaporanSampahRepository;
import com.example.trashformer.repository.PembayaranSampahRepository;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.SetoranSampahRepository;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.DashboardService;
import com.example.trashformer.service.ReportingService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final WargaRepository wargaRepository;
    private final PetugasRepository petugasRepository;
    private final SetoranSampahRepository setoranSampahRepository;
    private final LaporanSampahRepository laporanSampahRepository;
    private final PembayaranSampahRepository pembayaranSampahRepository;
    private final ReportingService reportingService;

    public DashboardServiceImpl(
            UserRepository userRepository,
            WargaRepository wargaRepository,
            PetugasRepository petugasRepository,
            SetoranSampahRepository setoranSampahRepository,
            LaporanSampahRepository laporanSampahRepository,
            PembayaranSampahRepository pembayaranSampahRepository,
            ReportingService reportingService
    ) {
        this.userRepository = userRepository;
        this.wargaRepository = wargaRepository;
        this.petugasRepository = petugasRepository;
        this.setoranSampahRepository = setoranSampahRepository;
        this.laporanSampahRepository = laporanSampahRepository;
        this.pembayaranSampahRepository = pembayaranSampahRepository;
        this.reportingService = reportingService;
    }

    @Override
    public AdminDashboardResponse getAdminDashboard() {
        LocalDate today = LocalDate.now();
        LocalDate startMonth = today.withDayOfMonth(1);
        LocalDate startYear = today.withDayOfYear(1);

        List<SetoranSampah> todayRows = setoranSampahRepository.findAll(
                com.example.trashformer.specification.SetoranSpecifications.withFilter(
                        new LaporanFilterRequest(today, today, null, null, null, null, null)
                )
        );
        List<SetoranSampah> monthRows = setoranSampahRepository.findAll(
                com.example.trashformer.specification.SetoranSpecifications.withFilter(
                        new LaporanFilterRequest(startMonth, today, null, null, null, null, null)
                )
        );
        List<SetoranSampah> yearRows = setoranSampahRepository.findAll(
                com.example.trashformer.specification.SetoranSpecifications.withFilter(
                        new LaporanFilterRequest(startYear, today, null, null, null, null, null)
                )
        );

        List<CategoryWasteSummaryResponse> byType = reportingService.summarizeByTipeSampah(new LaporanFilterRequest(null, null, null, null, null, null, null));
        List<CategoryWasteSummaryResponse> byPetugas = reportingService.summarizeByPetugas(new LaporanFilterRequest(null, null, null, null, null, null, null));
        List<RoleWasteSummaryResponse> byRole = reportingService.summarizeByRole(new LaporanFilterRequest(null, null, null, null, null, null, null));

        return new AdminDashboardResponse(
                userRepository.count(),
                userRepository.countByRole(Role.ADMIN),
                userRepository.countByRole(Role.PETUGAS),
                userRepository.countByRole(Role.WARGA),
                (long) todayRows.size(),
                sumWeights(todayRows),
                (long) monthRows.size(),
                sumWeights(monthRows),
                (long) yearRows.size(),
                sumWeights(yearRows),
                setoranSampahRepository.countByStatus(StatusSetoran.MENUNGGU_VERIFIKASI),
                laporanSampahRepository.countByStatusLaporan(StatusLaporan.BARU),
                pembayaranSampahRepository.countByStatus(StatusPembayaran.MENUNGGU_KONFIRMASI),
                byType,
                byPetugas,
                byRole
        );
    }

    @Override
    public PetugasDashboardResponse getPetugasDashboard(Long petugasId) {
        Petugas petugas = petugasRepository.findById(petugasId)
                .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + petugasId));

        List<SetoranSampah> allRows = setoranSampahRepository.findByPetugasIdOrderByTanggalSetoranDescIdDesc(petugasId);
        List<SetoranSampah> todayRows = allRows.stream()
                .filter(setoran -> setoran.getTanggalSetoran().equals(LocalDate.now()))
                .toList();

        List<LaporanSampah> accessibleReports = laporanSampahRepository.findAllByOrderByTanggalLaporanDescIdDesc().stream()
                .filter(laporan -> laporan.getPetugas() == null || laporan.getPetugas().getId().equals(petugasId))
                .toList();

        List<SetoranSampahResponse> latestSetoran = setoranSampahRepository.findTop5ByPetugasIdOrderByTanggalSetoranDescIdDesc(petugasId).stream()
                .map(DtoMapper::toSetoranSampahResponse)
                .toList();
        List<LaporanSampahResponse> latestLaporan = accessibleReports.stream()
                .limit(5)
                .map(DtoMapper::toLaporanSampahResponse)
                .toList();

        return new PetugasDashboardResponse(
                petugas.getId(),
                petugas.getUser().getNama(),
                (long) allRows.size(),
                sumWeights(allRows),
                (long) todayRows.size(),
                sumWeights(todayRows),
                accessibleReports.stream().filter(laporan -> laporan.getStatusLaporan() == StatusLaporan.DIPROSES).count(),
                accessibleReports.stream().filter(laporan -> laporan.getStatusLaporan() == StatusLaporan.BARU).count(),
                latestSetoran,
                latestLaporan
        );
    }

    @Override
    public WargaDashboardResponse getWargaDashboard(Long wargaId) {
        Warga warga = wargaRepository.findById(wargaId)
                .orElseThrow(() -> new ResourceNotFoundException("Warga tidak ditemukan: " + wargaId));

        List<SetoranSampah> allRows = setoranSampahRepository.findByWargaIdOrderByTanggalSetoranDescIdDesc(wargaId);
        List<SetoranSampahResponse> latestSetoran = setoranSampahRepository.findTop5ByWargaIdOrderByTanggalSetoranDescIdDesc(wargaId).stream()
                .map(DtoMapper::toSetoranSampahResponse)
                .toList();
        List<LaporanSampahResponse> latestLaporan = laporanSampahRepository.findTop5ByWargaIdOrderByTanggalLaporanDescIdDesc(wargaId).stream()
                .map(DtoMapper::toLaporanSampahResponse)
                .toList();
        List<PembayaranSampah> pembayaranRows = pembayaranSampahRepository.findByWargaIdOrderByTanggalPembayaranDescIdDesc(wargaId);
        List<PembayaranSampahResponse> latestPembayaran = pembayaranSampahRepository.findTop5ByWargaIdOrderByTanggalPembayaranDescIdDesc(wargaId).stream()
                .map(DtoMapper::toPembayaranSampahResponse)
                .toList();

        return new WargaDashboardResponse(
                warga.getId(),
                warga.getUser().getNama(),
                (long) allRows.size(),
                sumWeights(allRows),
                allRows.stream().filter(setoran -> setoran.getStatus() == StatusSetoran.DITERIMA).count(),
                allRows.stream().filter(setoran -> setoran.getStatus() == StatusSetoran.MENUNGGU_VERIFIKASI).count(),
                laporanSampahRepository.countByWargaId(wargaId),
                (long) pembayaranRows.size(),
                pembayaranRows.stream().filter(pembayaran -> pembayaran.getStatus() == StatusPembayaran.MENUNGGU_KONFIRMASI).count(),
                pembayaranRows.stream().filter(pembayaran -> pembayaran.getStatus() == StatusPembayaran.LUNAS).count(),
                pembayaranRows.stream()
                        .filter(pembayaran -> pembayaran.getStatus() == StatusPembayaran.LUNAS)
                        .map(PembayaranSampah::getNominal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                latestSetoran,
                latestLaporan,
                latestPembayaran
        );
    }

    private BigDecimal sumWeights(List<SetoranSampah> rows) {
        return rows.stream()
                .map(SetoranSampah::getBeratKg)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
