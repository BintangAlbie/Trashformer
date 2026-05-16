package com.example.trashformer.service;

import com.example.trashformer.dto.response.AdminDashboardResponse;
import com.example.trashformer.dto.response.PetugasDashboardResponse;
import com.example.trashformer.dto.response.WargaDashboardResponse;

public interface DashboardService {

    AdminDashboardResponse getAdminDashboard();

    PetugasDashboardResponse getPetugasDashboard(Long petugasId);

    WargaDashboardResponse getWargaDashboard(Long wargaId);
}
