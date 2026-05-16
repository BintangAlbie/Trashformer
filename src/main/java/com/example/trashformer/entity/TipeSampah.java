package com.example.trashformer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "tipe_sampah")
public class TipeSampah extends BaseEntity {

    @Column(name = "nama_tipe", nullable = false, unique = true, length = 100)
    private String namaTipe;

    @Column(length = 255)
    private String deskripsi;

    @Column(name = "harga_per_kg", nullable = false, precision = 12, scale = 2)
    private BigDecimal hargaPerKg;

    public String getNamaTipe() {
        return namaTipe;
    }

    public void setNamaTipe(String namaTipe) {
        this.namaTipe = namaTipe;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public BigDecimal getHargaPerKg() {
        return hargaPerKg;
    }

    public void setHargaPerKg(BigDecimal hargaPerKg) {
        this.hargaPerKg = hargaPerKg;
    }
}
