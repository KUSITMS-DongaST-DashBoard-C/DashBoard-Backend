package com.example.dashboardback.user.service;

import com.example.dashboardback.global.config.security.jwt.JwtTokenProvider;
import com.example.dashboardback.global.dto.TokenInfoResponse;
import com.example.dashboardback.user.constant.UserConstants;
import com.example.dashboardback.user.constant.UserConstants.EToken;
import com.example.dashboardback.user.dto.UserDto;
import com.example.dashboardback.user.dto.UserDto.LoginRequest;
import com.example.dashboardback.user.dto.UserDto.LoginResponse;
import com.example.dashboardback.user.dto.UserMapper;
import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.user.exception.NotFoundEmailException;
import com.example.dashboardback.user.exception.OverlapUserException;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;

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

        redisTemplate.opsForValue().set(EToken.eRefreshToken.getMessage() + authentication.getName(),
                authentication.getName(),tokenInfoResponse.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);

        return tokenInfoResponse;
    }

    @Override
    public UserDto.UserInfoResponse getUserInfo(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        List<UserDto.ActiveUserResponse> activeUserResponseList = new ArrayList<>();
        getActiveUser(email, activeUserResponseList);
        return UserDto.UserInfoResponse.from(user.getName(), email, user.getUserImage().getImageUrl(), activeUserResponseList);
    }

    public void getActiveUser(String email, List<UserDto.ActiveUserResponse> activeUserResponseList){

        Set<String> redisSessionKeys = redisTemplate.keys("RT:*");

        for (String redisSessionKey : redisSessionKeys) {
            String redisSessionValue = (String)redisTemplate.opsForValue().get(redisSessionKey);
            User user = validateEmail(redisSessionValue);
            if (user.getEmail().equals(email)) {
                continue;
            }
            UserDto.ActiveUserResponse dto = UserDto.ActiveUserResponse.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .imgUrl(user.getUserImage().getImageUrl())
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
    public User validateEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

}
