package com.example.trashformer.entity;

import com.example.trashformer.enums.StatusPembayaran;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pembayaran_sampah")
public class PembayaranSampah extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warga_id", nullable = false)
    private Warga warga;

    @Column(name = "periode_tagihan", nullable = false, length = 100)
    private String periodeTagihan;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal nominal;

    @Column(name = "tanggal_pembayaran", nullable = false)
    private LocalDate tanggalPembayaran;

    @Column(name = "metode_pembayaran", nullable = false, length = 50)
    private String metodePembayaran;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusPembayaran status;

    @Column(length = 500)
    private String keterangan;

    @Column(name = "catatan_verifikasi", length = 500)
    private String catatanVerifikasi;

    @Column(name = "bukti_pembayaran_url", length = 255)
    private String buktiPembayaranUrl;

    public Warga getWarga() {
        return warga;
    }

    public void setWarga(Warga warga) {
        this.warga = warga;
    }

    public String getPeriodeTagihan() {
        return periodeTagihan;
    }

    public void setPeriodeTagihan(String periodeTagihan) {
        this.periodeTagihan = periodeTagihan;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public LocalDate getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(LocalDate tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public StatusPembayaran getStatus() {
        return status;
    }

    public void setStatus(StatusPembayaran status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCatatanVerifikasi() {
        return catatanVerifikasi;
    }

    public void setCatatanVerifikasi(String catatanVerifikasi) {
        this.catatanVerifikasi = catatanVerifikasi;
    }

    public String getBuktiPembayaranUrl() {
        return buktiPembayaranUrl;
    }

    public void setBuktiPembayaranUrl(String buktiPembayaranUrl) {
        this.buktiPembayaranUrl = buktiPembayaranUrl;
    }
}
