package com.example.trashformer.dto.response;

import com.example.trashformer.enums.Role;

import java.math.BigDecimal;

public record RoleWasteSummaryResponse(
        Role role,
        Long jumlahUser,
        Long jumlahAktivitas,
        BigDecimal totalBeratKg
) {
}
