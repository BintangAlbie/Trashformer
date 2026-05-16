package com.example.trashformer.repository;

import com.example.trashformer.entity.User;
import com.example.trashformer.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRole(Role role);

    long countByRole(Role role);

    List<User> findAllByOrderByCreatedAtDesc();
}
