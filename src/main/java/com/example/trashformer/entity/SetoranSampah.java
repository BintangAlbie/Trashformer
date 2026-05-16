package com.example.trashformer.entity;

import com.example.trashformer.enums.StatusSetoran;
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
@Table(name = "setoran_sampah")
public class SetoranSampah extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warga_id", nullable = false)
    private Warga warga;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "petugas_id", nullable = false)
    private Petugas petugas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipe_sampah_id", nullable = false)
    private TipeSampah tipeSampah;

    @Column(name = "berat_kg", nullable = false, precision = 10, scale = 2)
    private BigDecimal beratKg;

    @Column(name = "tanggal_setoran", nullable = false)
    private LocalDate tanggalSetoran;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusSetoran status;

    @Column(length = 500)
    private String catatan;

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

    public BigDecimal getBeratKg() {
        return beratKg;
    }

    public void setBeratKg(BigDecimal beratKg) {
        this.beratKg = beratKg;
    }

    public LocalDate getTanggalSetoran() {
        return tanggalSetoran;
    }

    public void setTanggalSetoran(LocalDate tanggalSetoran) {
        this.tanggalSetoran = tanggalSetoran;
    }

    public StatusSetoran getStatus() {
        return status;
    }

    public void setStatus(StatusSetoran status) {
        this.status = status;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
