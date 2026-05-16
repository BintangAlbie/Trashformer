package com.example.trashformer.service.impl;

import com.example.trashformer.dto.request.AdminUserRequest;
import com.example.trashformer.dto.response.UserResponse;
import com.example.trashformer.entity.User;
import com.example.trashformer.enums.Role;
import com.example.trashformer.exception.BusinessException;
import com.example.trashformer.exception.ResourceNotFoundException;
import com.example.trashformer.repository.UserRepository;
import com.example.trashformer.service.UserService;
import com.example.trashformer.util.DtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createAdmin(AdminUserRequest request) {
        if (userRepository.existsByRole(Role.ADMIN)) {
            throw new BusinessException("Akun ADMIN utama sudah ada.");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Email sudah digunakan.");
        }

        User user = new User();
        user.setNama(request.nama());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.ADMIN);

        return DtoMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(DtoMapper::toUserResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User tidak ditemukan: " + id));
        return DtoMapper.toUserResponse(user);
    }
}
