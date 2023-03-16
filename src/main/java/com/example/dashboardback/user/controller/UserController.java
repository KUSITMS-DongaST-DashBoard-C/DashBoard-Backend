package com.example.dashboardback.user.controller;

import com.example.dashboardback.global.dto.ResponseDto;
import com.example.dashboardback.user.constant.UserConstants;
import com.example.dashboardback.user.dto.UserDto;
import com.example.dashboardback.user.dto.UserDto.LoginResponse;
import com.example.dashboardback.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Enumeration;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Api(tags = "User API")
@Slf4j
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> singupUser(@Valid @ModelAttribute UserDto.SignupRequest signupRequest) {
        this.userService.singup(signupRequest);
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.SIGNUP_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "로그인", notes = "로그인을 합니다.")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponse>> loginUser(@Valid @ModelAttribute UserDto.LoginRequest loginRequest, HttpSession httpSession) {
        LoginResponse loginResponse = this.userService.login(loginRequest, httpSession);
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.LOGIN_SUCCESS.getMessage(),loginResponse));
    }

    @GetMapping("/info")
    public ResponseEntity<ResponseDto<UserDto.UserInfoResponse>> getUserInfo(HttpSession httpSession){
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage. GETUSERINFO_SUCCESS.getMessage(),this.userService.getUserInfo(httpSession)));
    }


}
