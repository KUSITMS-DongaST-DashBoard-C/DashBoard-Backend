package com.example.dashboardback.admin.repository;

import com.example.dashboardback.admin.entity.Admin;

import java.util.Optional;

public interface AdminRepositoryCustom {
    Optional<Admin> findByEmail(String email);
}
