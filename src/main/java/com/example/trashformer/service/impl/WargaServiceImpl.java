package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.WargaRequest;
import com.example.trashformer.dto.response.WargaResponse;
import com.example.trashformer.entity.User;
import com.example.trashformer.entity.Warga;
import com.example.trashformer.enums.Role;
import com.example.trashformer.exception.BusinessException;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.repository.WargaRepository;
import com.example.trashformer.service.WargaService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WargaServiceImpl implements WargaService {

    private final WargaRepository wargaRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WargaServiceImpl(WargaRepository wargaRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.wargaRepository = wargaRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public WargaResponse createWarga(WargaRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email sudah digunakan.");
        }
        if (wargaRepository.existsByNik(request.nik())) {
            throw new BusinessException("NIK sudah terdaftar.");
        }

        User user = new User();
        user.setNama(request.nama());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.WARGA);

        Warga warga = new Warga();
        warga.setUser(userRepository.save(user));
        warga.setNik(request.nik());
        warga.setAlamat(request.alamat());
        warga.setNoHp(request.noHp());
        warga.setRt(request.rt());
        warga.setRw(request.rw());

        return DtoMapper.toWargaResponse(wargaRepository.save(warga));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WargaResponse> getAllWarga() {
        return wargaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(DtoMapper::toWargaResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public WargaResponse getWargaById(Long id) {
        Warga warga = wargaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warga tidak ditemukan: " + id));
        return DtoMapper.toWargaResponse(warga);
    }
}
