package com.example.trashformer.service;

import com.example.trashformer.dto.request.AdminUserRequest;
import com.example.trashformer.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createAdmin(AdminUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);
}
