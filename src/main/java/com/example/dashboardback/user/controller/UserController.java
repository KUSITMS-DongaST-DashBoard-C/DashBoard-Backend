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
    public ResponseEntity<ResponseDto<LoginResponse>> loginUser(@Valid @ModelAttribute UserDto.LoginRequest loginRequest) {
        LoginResponse loginResponse = this.userService.login(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.LOGIN_SUCCESS.getMessage(),loginResponse));
    }

    @ApiOperation(value = "관리자 정보", notes = "관리자 정보 및 활동중인 관리자 정보를 보여줍니다.")
    @GetMapping("/info")
    public ResponseEntity<ResponseDto<UserDto.UserInfoResponse>> getUserInfo(){
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage. GETUSERINFO_SUCCESS.getMessage(),this.userService.getUserInfo()));
    }


    @ApiOperation(value="로그아웃", notes = "유저를 로그아웃 시킵니다.")
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logoutUser(){
        this.userService.logout();
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.LOGOUT_SUCCESS.getMessage()));
    }

    @ApiOperation(value="전체 로그아웃", notes = "모든 유저를 로그아웃 시킵니다.")
    @PostMapping("/logoutAll")
    public ResponseEntity<ResponseDto> logoutUserAll(){
        this.userService.logoutAll();
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.LOGOUT_SUCCESS.getMessage()));
    }

}
