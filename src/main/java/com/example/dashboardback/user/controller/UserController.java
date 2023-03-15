package com.example.dashboardback.user.controller;

import com.example.dashboardback.global.dto.ResponseDto;
import com.example.dashboardback.user.constant.UserConstants;
import com.example.dashboardback.user.dto.UserDto;
import com.example.dashboardback.user.dto.UserDto.LoginResponse;
import com.example.dashboardback.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Api(tags = "User API")
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
        return ResponseEntity.ok(ResponseDto.create(UserConstants.EBoardResponseMessage.LOGIN_SUCCESS.getMessage(), this.userService.login(loginRequest)));
    }

}
