package com.example.trashformer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "hasil_laporan")
public class HasilLaporan extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "laporan_sampah_id", nullable = false, unique = true)
    private LaporanSampah laporanSampah;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "petugas_id", nullable = false)
    private Petugas petugas;

    @Column(name = "keterangan_hasil", nullable = false, columnDefinition = "TEXT")
    private String keteranganHasil;

    @Column(name = "tanggal_selesai", nullable = false)
    private LocalDate tanggalSelesai;

    @Column(name = "file_pdf_url", length = 255)
    private String filePdfUrl;

    public LaporanSampah getLaporanSampah() {
        return laporanSampah;
    }

    public void setLaporanSampah(LaporanSampah laporanSampah) {
        this.laporanSampah = laporanSampah;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public String getKeteranganHasil() {
        return keteranganHasil;
    }

    public void setKeteranganHasil(String keteranganHasil) {
        this.keteranganHasil = keteranganHasil;
    }

    public LocalDate getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(LocalDate tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getFilePdfUrl() {
        return filePdfUrl;
    }

    public void setFilePdfUrl(String filePdfUrl) {
        this.filePdfUrl = filePdfUrl;
    }
}
