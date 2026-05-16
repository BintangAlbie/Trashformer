package com.example.trashformer.service;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.ExportedReport;
import com.example.trashformer.dto.response.SetoranSampahResponse;

import java.util.List;

public interface ReportExportService {

    boolean supports(String format);

    ExportedReport export(String title, LaporanFilterRequest filter, List<SetoranSampahResponse> rows);
}
