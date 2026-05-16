package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.PetugasRequest;
import com.example.trashformer.dto.response.PetugasResponse;
import com.example.trashformer.entity.Petugas;
import com.example.trashformer.entity.User;
import com.example.trashformer.enums.Role;
import com.example.trashformer.exception.BusinessException;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.PetugasRepository;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.service.PetugasService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetugasServiceImpl implements PetugasService {

    private final PetugasRepository petugasRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PetugasServiceImpl(PetugasRepository petugasRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.petugasRepository = petugasRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PetugasResponse createPetugas(PetugasRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email sudah digunakan.");
        }
        if (petugasRepository.existsByNomorPetugas(request.nomorPetugas())) {
            throw new BusinessException("Nomor petugas sudah terdaftar.");
        }

        User user = new User();
        user.setNama(request.nama());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.PETUGAS);

        Petugas petugas = new Petugas();
        petugas.setUser(userRepository.save(user));
        petugas.setNomorPetugas(request.nomorPetugas());
        petugas.setAlamat(request.alamat());
        petugas.setNoHp(request.noHp());

        return DtoMapper.toPetugasResponse(petugasRepository.save(petugas));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetugasResponse> getAllPetugas() {
        return petugasRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(DtoMapper::toPetugasResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PetugasResponse getPetugasById(Long id) {
        Petugas petugas = petugasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Petugas tidak ditemukan: " + id));
        return DtoMapper.toPetugasResponse(petugas);
    }
}
