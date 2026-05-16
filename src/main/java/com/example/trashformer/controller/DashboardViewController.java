package com.example.trashformer.controller;

import com.example.trashformer.entity.User;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.DashboardService;
import com.example.trashformer.service.LaporanSampahService;
import com.example.trashformer.service.PembayaranSampahService;
import com.example.trashformer.service.TipeSampahService;
import com.example.trashformer.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardViewController {

    private final DashboardService dashboardService;
    private final UserRepository userRepository;
    private final WargaRepository wargaRepository;
    private final PetugasRepository petugasRepository;
    private final UserService userService;
    private final LaporanSampahService laporanSampahService;
    private final PembayaranSampahService pembayaranSampahService;
    private final TipeSampahService tipeSampahService;

    public DashboardViewController(
            DashboardService dashboardService,
            UserRepository userRepository,
            WargaRepository wargaRepository,
            PetugasRepository petugasRepository,
            UserService userService,
            LaporanSampahService laporanSampahService,
            PembayaranSampahService pembayaranSampahService,
            TipeSampahService tipeSampahService
    ) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
        this.wargaRepository = wargaRepository;
        this.petugasRepository = petugasRepository;
        this.userService = userService;
        this.laporanSampahService = laporanSampahService;
        this.pembayaranSampahService = pembayaranSampahService;
        this.tipeSampahService = tipeSampahService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (isAuthenticated(authentication)) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Authentication authentication) {
        if (isAuthenticated(authentication)) {
            return "redirect:/dashboard";
        }
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        User user = getCurrentUser(authentication);
        return switch (user.getRole()) {
            case ADMIN -> "redirect:/admin/dashboard";
            case PETUGAS -> "redirect:/petugas/dashboard";
            case WARGA -> "redirect:/warga/dashboard";
        };
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("dashboard", dashboardService.getAdminDashboard());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("laporanList", laporanSampahService.getAllLaporan());
        model.addAttribute("pembayaranList", pembayaranSampahService.getAllPembayaran());
        model.addAttribute("tipeSampahList", tipeSampahService.getAllTipeSampah());
        return "dashboards/admin";
    }

    @GetMapping("/dashboards/admin")
    public String legacyAdminDashboard() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/petugas/dashboard")
    public String petugasDashboard(Authentication authentication, Model model) {
        Long petugasId = petugasRepository.findByUserId(getCurrentUser(authentication).getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profil petugas tidak ditemukan."))
                .getId();

        model.addAttribute("dashboard", dashboardService.getPetugasDashboard(petugasId));
        model.addAttribute(
                "laporanList",
                laporanSampahService.getAllLaporan().stream()
                        .filter(laporan -> laporan.petugasId() == null || laporan.petugasId().equals(petugasId))
                        .toList()
        );
        return "dashboards/petugas";
    }

    @GetMapping("/dashboards/petugas/{petugasId}")
    public String legacyPetugasDashboard(@PathVariable Long petugasId) {
        return "redirect:/petugas/dashboard";
    }

    @GetMapping("/warga/dashboard")
    public String wargaDashboard(Authentication authentication, Model model) {
        Long wargaId = wargaRepository.findByUserId(getCurrentUser(authentication).getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profil warga tidak ditemukan."))
                .getId();

        model.addAttribute("dashboard", dashboardService.getWargaDashboard(wargaId));
        model.addAttribute("laporanList", laporanSampahService.getLaporanByWargaId(wargaId));
        model.addAttribute("pembayaranList", pembayaranSampahService.getPembayaranByWargaId(wargaId));
        model.addAttribute("tipeSampahList", tipeSampahService.getAllTipeSampah());
        return "dashboards/warga";
    }

    @GetMapping("/dashboards/warga/{wargaId}")
    public String legacyWargaDashboard(@PathVariable Long wargaId) {
        return "redirect:/warga/dashboard";
    }

    private User getCurrentUser(Authentication authentication) {
        if (!isAuthenticated(authentication)) {
            throw new ResourceNotFoundException("User belum login.");
        }

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan: " + authentication.getName()));
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null
                && authentication.isAuthenticated()
                && authentication.getAuthorities() != null
                && !"anonymousUser".equals(authentication.getName());
    }
}
