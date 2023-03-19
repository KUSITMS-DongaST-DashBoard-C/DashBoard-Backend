package com.example.dashboardback.admin.repository;

import com.example.dashboardback.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {
}
