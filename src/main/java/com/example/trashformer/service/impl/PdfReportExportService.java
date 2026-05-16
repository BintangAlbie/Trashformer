package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.dto.response.ExportedReport;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.service.ReportExportService;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class PdfReportExportService implements ReportExportService {

    @Override
    public boolean supports(String format) {
        return "PDF".equalsIgnoreCase(format);
    }

    @Override
    public ExportedReport export(String title, LaporanFilterRequest filter, List<SetoranSampahResponse> rows) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph("Tanggal cetak: " + LocalDate.now()));
            document.add(new Paragraph("Filter: " + describeFilter(filter)));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            addHeaderCell(table, "Tanggal");
            addHeaderCell(table, "Warga");
            addHeaderCell(table, "Petugas");
            addHeaderCell(table, "Tipe");
            addHeaderCell(table, "Berat (kg)");
            addHeaderCell(table, "Status");

            for (SetoranSampahResponse row : rows) {
                table.addCell(row.tanggalSetoran().toString());
                table.addCell(row.namaWarga());
                table.addCell(row.namaPetugas());
                table.addCell(row.namaTipeSampah());
                table.addCell(row.beratKg().toPlainString());
                table.addCell(row.status().name());
            }

            document.add(table);
            document.close();

            return new ExportedReport("laporan-setoran-trashformer.pdf", "application/pdf", outputStream.toByteArray());
        } catch (Exception exception) {
            throw new IllegalStateException("Gagal membuat PDF laporan.", exception);
        }
    }

    private void addHeaderCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
        table.addCell(cell);
    }

    private String describeFilter(LaporanFilterRequest filter) {
        if (filter == null) {
            return "Semua data";
        }
        return "awal=" + filter.tanggalAwal()
                + ", akhir=" + filter.tanggalAkhir()
                + ", warga=" + filter.wargaId()
                + ", petugas=" + filter.petugasId()
                + ", tipe=" + filter.tipeSampahId()
                + ", status=" + filter.status()
                + ", role=" + filter.role();
    }
}
