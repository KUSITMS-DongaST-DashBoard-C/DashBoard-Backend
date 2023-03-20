package com.example.dashboardback.loginhistory.repository;


public interface LoginHistoryCustom {
    long getDauByDay(int day);
    long getMauByMonth(int year, int month);
}
