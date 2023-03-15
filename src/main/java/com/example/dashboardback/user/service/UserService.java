package com.example.dashboardback.user.service;

import com.example.dashboardback.user.dto.UserDto;
import com.example.dashboardback.user.dto.UserDto.LoginResponse;
import com.example.dashboardback.user.dto.UserDto.SignupRequest;
import com.example.dashboardback.user.entity.User;

public interface UserService {
    void singup(SignupRequest signupRequest);
    User validateEmail(String email);
    LoginResponse login(UserDto.LoginRequest loginRequest);
}
