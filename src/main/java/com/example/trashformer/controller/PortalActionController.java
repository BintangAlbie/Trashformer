package com.example.trashformer.controller;

import com.example.trashformer.dto.request.PembayaranSampahRequest;
import com.example.trashformer.dto.request.ReviewLaporanRequest;
import com.example.trashformer.dto.request.UpdateStatusPembayaranRequest;
import com.example.trashformer.dto.request.WargaRequest;
import com.example.trashformer.entity.User;
import com.example.trashformer.enums.StatusLaporan;
import com.example.trashformer.enums.StatusPembayaran;
import com.example.trashformer.exception.BusinessException;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.LaporanSampahService;
import com.example.trashformer.service.PembayaranSampahService;
import com.example.trashformer.service.UploadStorageService;
import com.example.trashformer.service.WargaService;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@Validated
public class PortalActionController {

    private final WargaService wargaService;
    private final LaporanSampahService laporanSampahService;
    private final PembayaranSampahService pembayaranSampahService;
    private final UploadStorageService uploadStorageService;
    private final UserRepository userRepository;
    private final WargaRepository wargaRepository;
    private final PetugasRepository petugasRepository;

    public PortalActionController(
            WargaService wargaService,
            LaporanSampahService laporanSampahService,
            PembayaranSampahService pembayaranSampahService,
            UploadStorageService uploadStorageService,
            UserRepository userRepository,
            WargaRepository wargaRepository,
            PetugasRepository petugasRepository
    ) {
        this.wargaService = wargaService;
        this.laporanSampahService = laporanSampahService;
        this.pembayaranSampahService = pembayaranSampahService;
        this.uploadStorageService = uploadStorageService;
        this.userRepository = userRepository;
        this.wargaRepository = wargaRepository;
        this.petugasRepository = petugasRepository;
    }

    @PostMapping("/register")
    public String registerWarga(
            @RequestParam @NotBlank String nama,
            @RequestParam @NotBlank String email,
            @RequestParam @NotBlank String password,
            @RequestParam @NotBlank String nik,
            @RequestParam @NotBlank String alamat,
            @RequestParam @NotBlank String noHp,
            @RequestParam @NotBlank String rt,
            @RequestParam @NotBlank String rw,
            RedirectAttributes redirectAttributes
    ) {
        try {
            wargaService.createWarga(new WargaRequest(nama, email, password, nik, alamat, noHp, rt, rw));
            redirectAttributes.addFlashAttribute("successMessage", "Registrasi berhasil. Silakan login sebagai warga.");
            return "redirect:/login";
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/register";
        }
    }

    @PostMapping("/warga/laporan")
    public String createLaporan(
            Authentication authentication,
            @RequestParam(required = false) Long tipeSampahId,
            @RequestParam @NotBlank String judulLaporan,
            @RequestParam @NotBlank String deskripsi,
            @RequestParam @NotBlank String lokasi,
            @RequestParam(required = false) MultipartFile foto,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Long wargaId = getCurrentWargaId(authentication);
            String fotoUrl = uploadStorageService.store(foto, "laporan");

            laporanSampahService.createLaporan(new com.example.trashformer.dto.request.LaporanSampahRequest(
                    wargaId,
                    null,
                    tipeSampahId,
                    judulLaporan,
                    deskripsi,
                    lokasi,
                    LocalDate.now(),
                    StatusLaporan.BARU,
                    fotoUrl
            ));

            redirectAttributes.addFlashAttribute("successMessage", "Laporan sampah berhasil dikirim.");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/warga/dashboard";
    }

    @PostMapping("/warga/laporan/{id}/hapus")
    public String deleteLaporan(
            Authentication authentication,
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Long wargaId = getCurrentWargaId(authentication);
            if (!laporanSampahService.getLaporanById(id).wargaId().equals(wargaId)) {
                throw new BusinessException("Anda tidak boleh menghapus laporan milik warga lain.");
            }
            laporanSampahService.deleteLaporan(id);
            redirectAttributes.addFlashAttribute("successMessage", "Laporan berhasil dihapus.");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/warga/dashboard";
    }

    @PostMapping("/warga/pembayaran")
    public String createPembayaran(
            Authentication authentication,
            @RequestParam @NotBlank String periodeTagihan,
            @RequestParam @NotNull @DecimalMin("1.0") BigDecimal nominal,
            @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tanggalPembayaran,
            @RequestParam @NotBlank String metodePembayaran,
            @RequestParam(required = false) String keterangan,
            @RequestParam(required = false) MultipartFile buktiPembayaran,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Long wargaId = getCurrentWargaId(authentication);
            String buktiUrl = uploadStorageService.store(buktiPembayaran, "pembayaran");

            pembayaranSampahService.createPembayaran(new PembayaranSampahRequest(
                    wargaId,
                    periodeTagihan,
                    nominal,
                    tanggalPembayaran,
                    metodePembayaran,
                    StatusPembayaran.MENUNGGU_KONFIRMASI,
                    keterangan,
                    null,
                    buktiUrl
            ));

            redirectAttributes.addFlashAttribute("successMessage", "Pembayaran berhasil dikirim untuk diverifikasi admin.");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/warga/dashboard";
    }

    @PostMapping("/warga/pembayaran/{id}/hapus")
    public String deletePembayaran(
            Authentication authentication,
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Long wargaId = getCurrentWargaId(authentication);
            if (!pembayaranSampahService.getPembayaranById(id).wargaId().equals(wargaId)) {
                throw new BusinessException("Anda tidak boleh menghapus pembayaran milik warga lain.");
            }
            pembayaranSampahService.deletePembayaran(id);
            redirectAttributes.addFlashAttribute("successMessage", "Data pembayaran berhasil dihapus.");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/warga/dashboard";
    }

    @PostMapping("/petugas/laporan/{id}/review")
    public String reviewLaporan(
            Authentication authentication,
            @PathVariable Long id,
            @RequestParam @NotNull StatusLaporan statusLaporan,
            @RequestParam @NotBlank String keteranganHasil,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Long petugasId = getCurrentPetugasId(authentication);
            laporanSampahService.reviewLaporan(id, new ReviewLaporanRequest(petugasId, statusLaporan, keteranganHasil));
            redirectAttributes.addFlashAttribute("successMessage", "Status laporan berhasil diperbarui.");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/petugas/dashboard";
    }

    @PostMapping("/admin/pembayaran/{id}/status")
    public String updateStatusPembayaran(
            @PathVariable Long id,
            @RequestParam @NotNull StatusPembayaran status,
            @RequestParam(required = false) String catatanVerifikasi,
            RedirectAttributes redirectAttributes
    ) {
        try {
            pembayaranSampahService.updateStatus(id, new UpdateStatusPembayaranRequest(status, catatanVerifikasi));
            redirectAttributes.addFlashAttribute("successMessage", "Status pembayaran berhasil diperbarui.");
        } catch (RuntimeException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
        }

        return "redirect:/admin/dashboard";
    }

    private Long getCurrentWargaId(Authentication authentication) {
        return wargaRepository.findByUserId(getCurrentUser(authentication).getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profil warga tidak ditemukan."))
                .getId();
    }

    private Long getCurrentPetugasId(Authentication authentication) {
        return petugasRepository.findByUserId(getCurrentUser(authentication).getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profil petugas tidak ditemukan."))
                .getId();
    }

    private User getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getName())) {
            throw new ResourceNotFoundException("User belum login.");
        }

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan: " + authentication.getName()));
    }
}
