package com.example.dashboardback.user.service;

import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void makeUserData() {
        for(int i=0;i<100;i++){
            User newUser =  User.createUser();
            userRepository.save(newUser);
        }
    }

    @Override
    public void updateCreatedAtWithRandomDate(int daysAgo, int loginDaysAgo) {
        LocalDateTime now = LocalDateTime.now();

        for(Long i=1L;i<=200L;i++){
            LocalDateTime randomDateTime = now.minusDays(new Random().nextInt(daysAgo)).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime randomLoginTime= now.minusDays(new Random().nextInt(loginDaysAgo)).withMinute(0).withSecond(0).withNano(0);
            User user=userRepository.findById(i).orElseThrow();
            user.setCreatedAt(randomDateTime);
            user.setLoginTime(randomLoginTime);
        }
    }
}
