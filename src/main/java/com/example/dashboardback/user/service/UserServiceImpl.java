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

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

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
    public LoginResponse login(LoginRequest loginRequest, HttpSession httpSession) {
        TokenInfoResponse tokenInfoResponse = this.validateLogin(loginRequest, httpSession);
        return LoginResponse.from(tokenInfoResponse);
    }

    public TokenInfoResponse validateLogin(LoginRequest loginRequest, HttpSession httpSession) {
        //(1) 유저 시큐리티에 저장
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //(2) 로그인된 유저 세션에 저장
        httpSession.setAttribute(authentication.getName(),"login");

        // Redis에 세션 저장
        String sessionId = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(sessionId, authentication);

        //(3) 토큰 생성
        TokenInfoResponse tokenInfoResponse = this.tokenProvider.createToken(authentication);
        return tokenInfoResponse;
    }

    public UserDto.UserInfoResponse getUserInfo(HttpSession httpSession){
        //(1) 현 유저 정보 가져오기
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        //(2) 활동중인 유저 정보 List 형태로 반환
        List<UserDto.ActiveUserResponse> activeUserResponseList = new ArrayList<>();
        getActiveUser(email, activeUserResponseList,httpSession);

        return UserDto.UserInfoResponse.from(user.getName(), email, user.getUserImage().getImageUrl(), activeUserResponseList);

    }

    public void getActiveUser(String email, List<UserDto.ActiveUserResponse> activeUserResponseList, HttpSession httpSession){

        Set<String> redisSessionKeys = redisTemplate.keys("*");

        for (String redisSessionKey : redisSessionKeys) {
            User user = (User) redisTemplate.opsForValue().get(redisSessionKey);
            if (user.getEmail().equals(email)) {
                // 로그인한 유저는 제외하고 활성화된 사용자 목록에 추가
                continue;
            }
            UserDto.ActiveUserResponse dto = UserDto.ActiveUserResponse.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
                    
            activeUserResponseList.add(dto);
        }
    }

    public void logout(HttpSession httpSession){
        //(1) 현 유저 정보 가져오기
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // Redis에 저장된 세션 정보 삭제
        String sessionId = httpSession.getId();
        redisTemplate.delete(sessionId);
        //(2) 세션 정보 삭제
        httpSession.removeAttribute(email);
        //(3) 시큐리티에서 유저 삭제시키기
        SecurityContextHolder.clearContext();
    }


    @Override
    public User validateEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NotFoundEmailException::new);
    }

}
