package com.example.trashformer.controller;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.CategoryWasteSummaryResponse;
import com.example.trashformer.dto.response.DailyWasteSummaryResponse;
import com.example.trashformer.dto.response.ExportedReport;
import com.example.trashformer.dto.response.MonthlyWasteSummaryResponse;
import com.example.trashformer.dto.response.RoleWasteSummaryResponse;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.enums.Role;
import com.example.trashformer.enums.StatusSetoran;
import com.example.trashformer.service.ReportingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/setoran")
    public List<SetoranSampahResponse> getFilteredSetoran(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status,
            @RequestParam(required = false) Role role
    ) {
        return reportingService.getFilteredSetoran(buildFilter(tanggalAwal, tanggalAkhir, wargaId, petugasId, tipeSampahId, status, role));
    }

    @GetMapping("/daily")
    public List<DailyWasteSummaryResponse> getDailySummary(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status,
            @RequestParam(required = false) Role role
    ) {
        return reportingService.getDailySummary(buildFilter(tanggalAwal, tanggalAkhir, wargaId, petugasId, tipeSampahId, status, role));
    }

    @GetMapping("/monthly")
    public List<MonthlyWasteSummaryResponse> getMonthlySummary(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status,
            @RequestParam(required = false) Role role
    ) {
        return reportingService.getMonthlySummary(buildFilter(tanggalAwal, tanggalAkhir, wargaId, petugasId, tipeSampahId, status, role));
    }

    @GetMapping("/by-warga")
    public List<CategoryWasteSummaryResponse> getSummaryByWarga(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status
    ) {
        return reportingService.summarizeByWarga(buildFilter(tanggalAwal, tanggalAkhir, null, petugasId, tipeSampahId, status, null));
    }

    @GetMapping("/by-petugas")
    public List<CategoryWasteSummaryResponse> getSummaryByPetugas(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status
    ) {
        return reportingService.summarizeByPetugas(buildFilter(tanggalAwal, tanggalAkhir, wargaId, null, tipeSampahId, status, null));
    }

    @GetMapping("/by-tipe")
    public List<CategoryWasteSummaryResponse> getSummaryByTipe(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) StatusSetoran status
    ) {
        return reportingService.summarizeByTipeSampah(buildFilter(tanggalAwal, tanggalAkhir, wargaId, petugasId, null, status, null));
    }

    @GetMapping("/by-role")
    public List<RoleWasteSummaryResponse> getSummaryByRole(
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status,
            @RequestParam(required = false) Role role
    ) {
        return reportingService.summarizeByRole(buildFilter(tanggalAwal, tanggalAkhir, wargaId, petugasId, tipeSampahId, status, role));
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportReport(
            @RequestParam(defaultValue = "PDF") String format,
            @RequestParam(required = false) LocalDate tanggalAwal,
            @RequestParam(required = false) LocalDate tanggalAkhir,
            @RequestParam(required = false) Long wargaId,
            @RequestParam(required = false) Long petugasId,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam(required = false) StatusSetoran status,
            @RequestParam(required = false) Role role
    ) {
        ExportedReport report = reportingService.exportSetoranReport(
                buildFilter(tanggalAwal, tanggalAkhir, wargaId, petugasId, tipeSampahId, status, role),
                format
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.fileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, report.contentType())
                .body(report.content());
    }

    private LaporanFilterRequest buildFilter(
            LocalDate tanggalAwal,
            LocalDate tanggalAkhir,
            Long wargaId,
            Long petugasId,
            Long tipeSampahId,
            StatusSetoran status,
            Role role
    ) {
        return new LaporanFilterRequest(tanggalAwal, tanggalAkhir, wargaId, petugasId, tipeSampahId, status, role);
    }
}
