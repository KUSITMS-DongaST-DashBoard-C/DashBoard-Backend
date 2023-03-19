package com.example.dashboardback.admin.service;

import com.example.dashboardback.admin.dto.AdminDto;
import com.example.dashboardback.admin.dto.AdminDto.LoginResponse;
import com.example.dashboardback.admin.dto.AdminDto.SignupRequest;
import com.example.dashboardback.admin.entity.Admin;

public interface AdminService {
    void singup(SignupRequest signupRequest);
    Admin validateEmail(String email);
    LoginResponse login(AdminDto.LoginRequest loginRequest);
    AdminDto.UserInfoResponse getUserInfo();
    void logout();
    void logoutAll();
}
