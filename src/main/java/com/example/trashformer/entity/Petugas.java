package com.example.trashformer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "petugas")
public class Petugas extends AbstractProfile {

    @Column(name = "nomor_petugas", nullable = false, unique = true, length = 30)
    private String nomorPetugas;

    public String getNomorPetugas() {
        return nomorPetugas;
    }

    public void setNomorPetugas(String nomorPetugas) {
        this.nomorPetugas = nomorPetugas;
    }
}
