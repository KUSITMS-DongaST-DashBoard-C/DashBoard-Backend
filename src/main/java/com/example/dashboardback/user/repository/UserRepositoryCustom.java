package com.example.dashboardback.user.repository;

public interface UserRepositoryCustom {
    long getSignUpNumByDay(int day);
    long getSignUpNumByMonth(int year, int month);
}
