package com.example.trashformer.entity;

import com.example.trashformer.enums.StatusLaporan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "laporan_sampah")
public class LaporanSampah extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warga_id", nullable = false)
    private Warga warga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petugas_id")
    private Petugas petugas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipe_sampah_id")
    private TipeSampah tipeSampah;

    @Column(name = "judul_laporan", nullable = false, length = 150)
    private String judulLaporan;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String deskripsi;

    @Column(nullable = false, length = 255)
    private String lokasi;

    @Column(name = "tanggal_laporan", nullable = false)
    private LocalDate tanggalLaporan;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_laporan", nullable = false, length = 30)
    private StatusLaporan statusLaporan;

    @Column(name = "foto_url", length = 255)
    private String fotoUrl;

    @OneToOne(mappedBy = "laporanSampah", fetch = FetchType.LAZY)
    private HasilLaporan hasilLaporan;

    public Warga getWarga() {
        return warga;
    }

    public void setWarga(Warga warga) {
        this.warga = warga;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public TipeSampah getTipeSampah() {
        return tipeSampah;
    }

    public void setTipeSampah(TipeSampah tipeSampah) {
        this.tipeSampah = tipeSampah;
    }

    public String getJudulLaporan() {
        return judulLaporan;
    }

    public void setJudulLaporan(String judulLaporan) {
        this.judulLaporan = judulLaporan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public LocalDate getTanggalLaporan() {
        return tanggalLaporan;
    }

    public void setTanggalLaporan(LocalDate tanggalLaporan) {
        this.tanggalLaporan = tanggalLaporan;
    }

    public StatusLaporan getStatusLaporan() {
        return statusLaporan;
    }

    public void setStatusLaporan(StatusLaporan statusLaporan) {
        this.statusLaporan = statusLaporan;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public HasilLaporan getHasilLaporan() {
        return hasilLaporan;
    }

    public void setHasilLaporan(HasilLaporan hasilLaporan) {
        this.hasilLaporan = hasilLaporan;
    }
}
