package com.example.dashboardback.user.repository;

import java.time.LocalDateTime;

public interface UserRepositoryCustom {
    long getSignUpNumByDay(int day);
    long getSignUpNumByMonth(int year, int month);
    long getSignUpNumByWeek(LocalDateTime time);
}
