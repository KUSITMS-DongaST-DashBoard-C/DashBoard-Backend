package com.example.dashboardback.user.service;

import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void makeUserData() {
        for(int i=0;i<4;i++){
            User newUser =  User.createUser();
            userRepository.save(newUser);
        }
    }
}
