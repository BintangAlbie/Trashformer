package com.example.trashformer.config;

import com.example.trashformer.entity.TipeSampah;
import com.example.trashformer.entity.User;
import com.example.trashformer.entity.Warga;
import com.example.trashformer.entity.Petugas;
import com.example.trashformer.enums.Role;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.TipeSampahRepository;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.repository.WargaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TipeSampahRepository tipeSampahRepository;
    private final WargaRepository wargaRepository;
    private final PetugasRepository petugasRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            UserRepository userRepository,
            TipeSampahRepository tipeSampahRepository,
            WargaRepository wargaRepository,
            PetugasRepository petugasRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.tipeSampahRepository = tipeSampahRepository;
        this.wargaRepository = wargaRepository;
        this.petugasRepository = petugasRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedAdmin();
        seedDemoProfiles();
        seedTipeSampah();
    }

    private void seedAdmin() {
        if (userRepository.existsByRole(Role.ADMIN)) {
            return;
        }

        User admin = new User();
        admin.setNama("Admin Utama TRASHFORMER");
        admin.setEmail("admin@trashformer.local");
        admin.setPassword(passwordEncoder.encode("Admin123!"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }

    private void seedTipeSampah() {
        Map<String, BigDecimal> defaultTypes = new LinkedHashMap<>();
        defaultTypes.put("Organik", new BigDecimal("1200.00"));
        defaultTypes.put("Anorganik", new BigDecimal("1800.00"));
        defaultTypes.put("B3", new BigDecimal("2500.00"));
        defaultTypes.put("Plastik", new BigDecimal("2200.00"));
        defaultTypes.put("Kertas", new BigDecimal("1500.00"));
        defaultTypes.put("Logam", new BigDecimal("4500.00"));
        defaultTypes.put("Kaca", new BigDecimal("1700.00"));

        defaultTypes.forEach((name, price) -> {
            if (!tipeSampahRepository.existsByNamaTipeIgnoreCase(name)) {
                TipeSampah tipeSampah = new TipeSampah();
                tipeSampah.setNamaTipe(name);
                tipeSampah.setDeskripsi("Kategori sampah " + name + " untuk proses pengelolaan lingkungan.");
                tipeSampah.setHargaPerKg(price);
                tipeSampahRepository.save(tipeSampah);
            }
        });
    }

    private void seedDemoProfiles() {
        if (wargaRepository.count() == 0) {
            User wargaUser = new User();
            wargaUser.setNama("Warga Contoh");
            wargaUser.setEmail("warga@trashformer.local");
            wargaUser.setPassword(passwordEncoder.encode("Warga123!"));
            wargaUser.setRole(Role.WARGA);
            wargaUser = userRepository.save(wargaUser);

            Warga warga = new Warga();
            warga.setUser(wargaUser);
            warga.setNik("3201000000000001");
            warga.setAlamat("Jl. Hijau Bersih No. 1");
            warga.setNoHp("081234567890");
            warga.setRt("01");
            warga.setRw("05");
            wargaRepository.save(warga);
        }

        if (petugasRepository.count() == 0) {
            User petugasUser = new User();
            petugasUser.setNama("Petugas Contoh");
            petugasUser.setEmail("petugas@trashformer.local");
            petugasUser.setPassword(passwordEncoder.encode("Petugas123!"));
            petugasUser.setRole(Role.PETUGAS);
            petugasUser = userRepository.save(petugasUser);

            Petugas petugas = new Petugas();
            petugas.setUser(petugasUser);
            petugas.setNomorPetugas("PTG-001");
            petugas.setAlamat("Pos Kebersihan RW 05");
            petugas.setNoHp("081298765432");
            petugasRepository.save(petugas);
        }
    }
}
