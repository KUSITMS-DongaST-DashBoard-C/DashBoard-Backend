package com.example.dashboardback.admin.service;

import com.example.dashboardback.admin.dto.AdminMapper;
import com.example.dashboardback.admin.entity.Admin;
import com.example.dashboardback.global.config.security.jwt.JwtTokenProvider;
import com.example.dashboardback.global.dto.TokenInfoResponse;
import com.example.dashboardback.admin.constant.AdminConstants;
import com.example.dashboardback.admin.constant.AdminConstants.EToken;
import com.example.dashboardback.admin.dto.AdminDto;
import com.example.dashboardback.admin.dto.AdminDto.LoginRequest;
import com.example.dashboardback.admin.dto.AdminDto.LoginResponse;
import com.example.dashboardback.admin.exception.NotFoundEmailException;
import com.example.dashboardback.admin.exception.OverlapAdminException;
import com.example.dashboardback.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;

    @Override
    public void singup(AdminDto.SignupRequest signupRequest) {
        this.validateOverlap(signupRequest.getEmail());
        Admin admin = userMapper.toEntity(signupRequest);
        admin.encryptPassword(passwordEncoder);
        admin.setRole(AdminConstants.Role.ROLE_ADMIN);
        this.adminRepository.save(admin);
    }

    private void validateOverlap(String email) {
        adminRepository.findByEmail(email)
                .ifPresent((m -> {
                    throw new OverlapAdminException();
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

        redisTemplate.opsForValue().set(EToken.eRefreshToken.getMessage() + authentication.getName(),
                authentication.getName(),tokenInfoResponse.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfoResponse;
    }

    @Override
    public AdminDto.UserInfoResponse getUserInfo(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByEmail(email).orElseThrow();
        List<AdminDto.ActiveUserResponse> activeUserResponseList = new ArrayList<>();
        getActiveUser(email, activeUserResponseList);
        return AdminDto.UserInfoResponse.from(admin.getName(), email, admin.getAdminImage().getImageUrl(), activeUserResponseList);
    }

    public void getActiveUser(String email, List<AdminDto.ActiveUserResponse> activeUserResponseList){

        Set<String> redisSessionKeys = redisTemplate.keys("RT:*");

        for (String redisSessionKey : redisSessionKeys) {
            String redisSessionValue = (String)redisTemplate.opsForValue().get(redisSessionKey);
            Admin admin = validateEmail(redisSessionValue);
            if (admin.getEmail().equals(email)) {
                continue;
            }
            AdminDto.ActiveUserResponse dto = AdminDto.ActiveUserResponse.builder()
                    .name(admin.getName())
                    .email(admin.getEmail())
                    .imgUrl(admin.getAdminImage().getImageUrl())
                    .build();
                    
            activeUserResponseList.add(dto);
        }
    }

    @Override
    public void logout(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        redisTemplate.delete(EToken.eRefreshToken.getMessage() + email);
        SecurityContextHolder.clearContext();
    }

    @Override
    public void logoutAll() {
        Set<String> redisSessionKeys = redisTemplate.keys("*");
        redisTemplate.delete(redisSessionKeys);
        SecurityContextHolder.clearContext();
    }


    @Override
    public Admin validateEmail(String email) {
        return this.adminRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

}
