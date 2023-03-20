package com.example.dashboardback.loginhistory.repository;


import java.time.LocalDateTime;

public interface LoginHistoryCustom {
    long getDauByDay(int day);
    long getMauByMonth(int year, int month);
    long getWauByWeek(LocalDateTime time);
}
