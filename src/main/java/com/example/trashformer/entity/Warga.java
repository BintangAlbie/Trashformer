package com.example.trashformer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "warga")
public class Warga extends AbstractProfile {

    @Column(nullable = false, unique = true, length = 32)
    private String nik;

    @Column(nullable = false, length = 10)
    private String rt;

    @Column(nullable = false, length = 10)
    private String rw;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }
}
