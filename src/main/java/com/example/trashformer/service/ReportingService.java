package com.example.trashformer.service;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.CategoryWasteSummaryResponse;
import com.example.trashformer.dto.response.DailyWasteSummaryResponse;
import com.example.trashformer.dto.response.ExportedReport;
import com.example.trashformer.dto.response.MonthlyWasteSummaryResponse;
import com.example.trashformer.dto.response.RoleWasteSummaryResponse;
import com.example.trashformer.dto.response.SetoranSampahResponse;

import java.util.List;

public interface ReportingService {

    List<SetoranSampahResponse> getFilteredSetoran(LaporanFilterRequest filter);

    List<DailyWasteSummaryResponse> getDailySummary(LaporanFilterRequest filter);

    List<MonthlyWasteSummaryResponse> getMonthlySummary(LaporanFilterRequest filter);

    List<CategoryWasteSummaryResponse> summarizeByWarga(LaporanFilterRequest filter);

    List<CategoryWasteSummaryResponse> summarizeByPetugas(LaporanFilterRequest filter);

    List<CategoryWasteSummaryResponse> summarizeByTipeSampah(LaporanFilterRequest filter);

    List<RoleWasteSummaryResponse> summarizeByRole(LaporanFilterRequest filter);

    ExportedReport exportSetoranReport(LaporanFilterRequest filter, String format);
}
