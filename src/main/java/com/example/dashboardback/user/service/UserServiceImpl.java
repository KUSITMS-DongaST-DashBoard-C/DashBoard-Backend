package com.example.dashboardback.user.service;

import com.example.dashboardback.global.config.security.jwt.JwtTokenProvider;
import com.example.dashboardback.global.dto.TokenInfoResponse;
import com.example.dashboardback.user.constant.UserConstants;
import com.example.dashboardback.user.dto.UserDto;
import com.example.dashboardback.user.dto.UserDto.LoginRequest;
import com.example.dashboardback.user.dto.UserDto.LoginResponse;
import com.example.dashboardback.user.dto.UserMapper;
import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.user.exception.NotFoundEmailException;
import com.example.dashboardback.user.exception.OverlapUserException;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public void singup(UserDto.SignupRequest signupRequest) {
        this.validateOverlap(signupRequest.getEmail());
        User user = userMapper.toEntity(signupRequest);
        user.encryptPassword(passwordEncoder);
        user.setRole(UserConstants.Role.ROLE_ADMIN);
        this.userRepository.save(user);
    }

    private void validateOverlap(String email) {
        userRepository.findByEmail(email)
                .ifPresent((m -> {
                    throw new OverlapUserException();
                }));
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        TokenInfoResponse tokenInfoResponse = this.validateLogin(loginRequest);
        return LoginResponse.from(tokenInfoResponse);
    }

    public TokenInfoResponse validateLogin(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        TokenInfoResponse tokenInfoResponse = this.tokenProvider.createToken(authentication);
        return tokenInfoResponse;
    }

    @Override
    public User validateEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

}
