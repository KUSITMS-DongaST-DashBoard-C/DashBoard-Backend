package com.example.dashboardback.user.service;

import com.example.dashboardback.user.entity.User;

public interface UserService {

    void makeUserData();
    void updateCreatedAtWithRandomDate(int daysAgo,int loginDaysAgo);
}
