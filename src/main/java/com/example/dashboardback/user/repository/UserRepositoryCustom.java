package com.example.dashboardback.user.repository;

import com.example.dashboardback.user.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
