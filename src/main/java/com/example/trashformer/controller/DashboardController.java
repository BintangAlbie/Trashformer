package com.example.trashformer.controller;

import com.example.trashformer.dto.response.AdminDashboardResponse;
import com.example.trashformer.dto.response.PetugasDashboardResponse;
import com.example.trashformer.dto.response.WargaDashboardResponse;
import com.example.trashformer.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin")
    public AdminDashboardResponse getAdminDashboard() {
        return dashboardService.getAdminDashboard();
    }

    @GetMapping("/petugas/{petugasId}")
    public PetugasDashboardResponse getPetugasDashboard(@PathVariable Long petugasId) {
        return dashboardService.getPetugasDashboard(petugasId);
    }

    @GetMapping("/warga/{wargaId}")
    public WargaDashboardResponse getWargaDashboard(@PathVariable Long wargaId) {
        return dashboardService.getWargaDashboard(wargaId);
    }
}
