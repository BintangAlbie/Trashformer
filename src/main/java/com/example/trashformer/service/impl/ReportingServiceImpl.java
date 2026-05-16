package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.CategoryWasteSummaryResponse;
import com.example.trashformer.dto.response.DailyWasteSummaryResponse;
import com.example.trashformer.dto.response.ExportedReport;
import com.example.trashformer.dto.response.MonthlyWasteSummaryResponse;
import com.example.trashformer.dto.response.RoleWasteSummaryResponse;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.entity.SetoranSampah;
import com.example.trashformer.enums.Role;
import com.example.trashformer.repository.SetoranSampahRepository;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.service.ReportExportService;
import com.example.trashformer.service.ReportingService;
import com.example.trashformer.specification.SetoranSpecifications;
import com.example.trashformer.util.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReportingServiceImpl implements ReportingService {

    private final SetoranSampahRepository setoranSampahRepository;
    private final UserRepository userRepository;
    private final List<ReportExportService> exportServices;

    public ReportingServiceImpl(
            SetoranSampahRepository setoranSampahRepository,
            UserRepository userRepository,
            List<ReportExportService> exportServices
    ) {
        this.setoranSampahRepository = setoranSampahRepository;
        this.userRepository = userRepository;
        this.exportServices = exportServices;
    }

    @Override
    public List<SetoranSampahResponse> getFilteredSetoran(LaporanFilterRequest filter) {
        return findFilteredEntities(filter).stream()
                .map(DtoMapper::toSetoranSampahResponse)
                .toList();
    }

    @Override
    public List<DailyWasteSummaryResponse> getDailySummary(LaporanFilterRequest filter) {
        if (isDateOnlyFilter(filter) && filter != null && filter.tanggalAwal() != null && filter.tanggalAkhir() != null) {
            return setoranSampahRepository.findDailySummary(filter.tanggalAwal(), filter.tanggalAkhir());
        }

        Map<java.time.LocalDate, List<SetoranSampah>> grouped = findFilteredEntities(filter).stream()
                .collect(Collectors.groupingBy(SetoranSampah::getTanggalSetoran, TreeMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> new DailyWasteSummaryResponse(entry.getKey(), sumWeights(entry.getValue()), (long) entry.getValue().size()))
                .toList();
    }

    @Override
    public List<MonthlyWasteSummaryResponse> getMonthlySummary(LaporanFilterRequest filter) {
        if (isDateOnlyFilter(filter) && filter != null && filter.tanggalAwal() != null && filter.tanggalAkhir() != null) {
            return setoranSampahRepository.findMonthlySummary(filter.tanggalAwal(), filter.tanggalAkhir());
        }

        Map<YearMonth, List<SetoranSampah>> grouped = findFilteredEntities(filter).stream()
                .collect(Collectors.groupingBy(setoran -> YearMonth.from(setoran.getTanggalSetoran()), TreeMap::new, Collectors.toList()));

        return grouped.entrySet().stream()
                .map(entry -> new MonthlyWasteSummaryResponse(
                        entry.getKey().getYear(),
                        entry.getKey().getMonthValue(),
                        sumWeights(entry.getValue()),
                        (long) entry.getValue().size())
                )
                .toList();
    }

    @Override
    public List<CategoryWasteSummaryResponse> summarizeByWarga(LaporanFilterRequest filter) {
        return summarizeByLabel(findFilteredEntities(filter), setoran -> setoran.getWarga().getUser().getNama());
    }

    @Override
    public List<CategoryWasteSummaryResponse> summarizeByPetugas(LaporanFilterRequest filter) {
        return summarizeByLabel(findFilteredEntities(filter), setoran -> setoran.getPetugas().getUser().getNama());
    }

    @Override
    public List<CategoryWasteSummaryResponse> summarizeByTipeSampah(LaporanFilterRequest filter) {
        return summarizeByLabel(findFilteredEntities(filter), setoran -> setoran.getTipeSampah().getNamaTipe());
    }

    @Override
    public List<RoleWasteSummaryResponse> summarizeByRole(LaporanFilterRequest filter) {
        List<SetoranSampah> rows = findFilteredEntities(filter);
        List<RoleWasteSummaryResponse> summaries = new ArrayList<>();

        summaries.add(new RoleWasteSummaryResponse(
                Role.ADMIN,
                userRepository.countByRole(Role.ADMIN),
                0L,
                BigDecimal.ZERO
        ));
        summaries.add(new RoleWasteSummaryResponse(
                Role.PETUGAS,
                userRepository.countByRole(Role.PETUGAS),
                (long) rows.stream().filter(setoran -> setoran.getPetugas().getUser().getRole() == Role.PETUGAS).count(),
                sumWeights(rows.stream().filter(setoran -> setoran.getPetugas().getUser().getRole() == Role.PETUGAS).toList())
        ));
        summaries.add(new RoleWasteSummaryResponse(
                Role.WARGA,
                userRepository.countByRole(Role.WARGA),
                (long) rows.stream().filter(setoran -> setoran.getWarga().getUser().getRole() == Role.WARGA).count(),
                sumWeights(rows.stream().filter(setoran -> setoran.getWarga().getUser().getRole() == Role.WARGA).toList())
        ));

        return summaries;
    }

    @Override
    public ExportedReport exportSetoranReport(LaporanFilterRequest filter, String format) {
        List<SetoranSampahResponse> rows = getFilteredSetoran(filter);
        ReportExportService exportService = exportServices.stream()
                .filter(service -> service.supports(format))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Format export tidak didukung: " + format));
        return exportService.export("Laporan Setoran TRASHFORMER", filter, rows);
    }

    private List<SetoranSampah> findFilteredEntities(LaporanFilterRequest filter) {
        return setoranSampahRepository.findAll(SetoranSpecifications.withFilter(filter));
    }

    private boolean isDateOnlyFilter(LaporanFilterRequest filter) {
        return filter != null
                && filter.wargaId() == null
                && filter.petugasId() == null
                && filter.tipeSampahId() == null
                && filter.status() == null
                && filter.role() == null;
    }

    private List<CategoryWasteSummaryResponse> summarizeByLabel(List<SetoranSampah> rows, Function<SetoranSampah, String> labelExtractor) {
        Map<String, List<SetoranSampah>> grouped = rows.stream()
                .collect(Collectors.groupingBy(labelExtractor));

        return grouped.entrySet().stream()
                .map(entry -> new CategoryWasteSummaryResponse(
                        entry.getKey(),
                        sumWeights(entry.getValue()),
                        (long) entry.getValue().size())
                )
                .sorted(Comparator.comparing(CategoryWasteSummaryResponse::totalBeratKg).reversed())
                .toList();
    }

    private BigDecimal sumWeights(List<SetoranSampah> rows) {
        return rows.stream()
                .map(SetoranSampah::getBeratKg)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
