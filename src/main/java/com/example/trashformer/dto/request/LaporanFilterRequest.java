package com.example.trashformer.dto.request;

import com.example.trashformer.enums.Role;
import com.example.trashformer.enums.StatusSetoran;

import java.time.LocalDate;

public record LaporanFilterRequest(
        LocalDate tanggalAwal,
        LocalDate tanggalAkhir,
        Long wargaId,
        Long petugasId,
        Long tipeSampahId,
        StatusSetoran status,
        Role role
) {
}
