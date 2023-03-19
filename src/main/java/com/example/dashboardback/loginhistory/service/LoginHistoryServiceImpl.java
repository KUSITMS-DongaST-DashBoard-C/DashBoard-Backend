package com.example.dashboardback.loginhistory.service;

import com.example.dashboardback.loginhistory.entity.LoginHistory;
import com.example.dashboardback.loginhistory.repository.LoginHistoryRepository;
import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginHistoryServiceImpl implements LoginHistoryService{
    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;

    @Override
    public void createLoginTime(){
        LocalDateTime now = LocalDateTime.of(2023,3,28,17,00);


        Long k=1L;
        for(Long i=1L;i<=140L;i++){
            for(Long j=1L; j<=5L;j++){
                User user=userRepository.findById(i).orElseThrow();
                LocalDateTime randomDateTime = now.minusDays(new Random().nextInt(200)).withMinute(0).withSecond(0).withNano(0);
                LoginHistory loginHistory=new LoginHistory();
                loginHistory.setUser(user);
                loginHistory.setLoginTime(randomDateTime);
                loginHistory.setLoginHistoryId(k++);
                loginHistoryRepository.save(loginHistory);
        }
    }for(Long i=150L;i<180L;i++){
            for(Long j=1L; j<=3L;j++){
                User user=userRepository.findById(i).orElseThrow();
                LocalDateTime randomDateTime = now.minusDays(new Random().nextInt(7)).withMinute(0).withSecond(0).withNano(0);
                LoginHistory loginHistory=new LoginHistory();
                loginHistory.setUser(user);
                loginHistory.setLoginTime(randomDateTime);
                loginHistory.setLoginHistoryId(k++);
                loginHistoryRepository.save(loginHistory);
            }
        }
    }
}
