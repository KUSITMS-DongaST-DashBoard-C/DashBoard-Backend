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
        LocalDateTime now = LocalDateTime.of(2023,03,28,17,00,00,00);

        for(Long i=1L;i<=30L;i++){
            LocalDateTime randomDateTime = now.minusDays(new Random().nextInt(7)).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime randomLoginTime= now.minusDays(new Random().nextInt(loginDaysAgo)).withHour(0).withMinute(0).withSecond(0).withNano(0);
            User user=userRepository.findById(i).orElseThrow();
            user.setCreatedAt(randomDateTime);
            user.setLoginTime(randomLoginTime);
        }for(Long i=31L;i<=200L;i++){
            LocalDateTime randomDateTime = now.minusDays(new Random().nextInt(daysAgo)).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime randomLoginTime= now.minusDays(new Random().nextInt(loginDaysAgo)).withHour(0).withMinute(0).withSecond(0).withNano(0);
            User user=userRepository.findById(i).orElseThrow();
            user.setCreatedAt(randomDateTime);
            user.setLoginTime(randomLoginTime);
        }
    }
}
