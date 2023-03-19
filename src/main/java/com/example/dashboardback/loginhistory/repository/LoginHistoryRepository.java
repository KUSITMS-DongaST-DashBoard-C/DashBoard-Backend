package com.example.dashboardback.loginhistory.repository;

import com.example.dashboardback.loginhistory.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long>, LoginHistoryCustom {
}
