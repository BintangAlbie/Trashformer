package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.ExportedReport;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.service.ReportExportService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrintReportExportService implements ReportExportService {

    @Override
    public boolean supports(String format) {
        return "PRINT".equalsIgnoreCase(format) || "HTML".equalsIgnoreCase(format);
    }

    @Override
    public ExportedReport export(String title, LaporanFilterRequest filter, List<SetoranSampahResponse> rows) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>")
                .append(title)
                .append("</title><style>")
                .append("body{font-family:Arial,sans-serif;background:#f6faf7;color:#1f2937;padding:24px;} ")
                .append("h1{color:#1f6f50;}table{width:100%;border-collapse:collapse;background:#fff;} ")
                .append("th,td{border:1px solid #d1d5db;padding:10px;text-align:left;} ")
                .append("th{background:#dff3e7;} .meta{margin-bottom:16px;color:#4b5563;} ")
                .append("@media print{body{padding:0;background:#fff;}}");
        html.append("</style></head><body>");
        html.append("<h1>").append(title).append("</h1>");
        html.append("<div class=\"meta\">Tanggal cetak: ").append(LocalDate.now()).append("<br>")
                .append("Filter: ").append(filter).append("</div>");
        html.append("<table><thead><tr><th>Tanggal</th><th>Warga</th><th>Petugas</th><th>Tipe</th><th>Berat (kg)</th><th>Status</th></tr></thead><tbody>");
        for (SetoranSampahResponse row : rows) {
            html.append("<tr><td>").append(row.tanggalSetoran())
                    .append("</td><td>").append(row.namaWarga())
                    .append("</td><td>").append(row.namaPetugas())
                    .append("</td><td>").append(row.namaTipeSampah())
                    .append("</td><td>").append(row.beratKg().toPlainString())
                    .append("</td><td>").append(row.status())
                    .append("</td></tr>");
        }
        html.append("</tbody></table></body></html>");

        return new ExportedReport(
                "laporan-setoran-trashformer.html",
                "text/html;charset=UTF-8",
                html.toString().getBytes(StandardCharsets.UTF_8)
        );
    }
}
