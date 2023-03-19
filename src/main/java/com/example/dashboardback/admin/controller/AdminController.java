package com.example.dashboardback.admin.controller;

import com.example.dashboardback.admin.constant.AdminConstants;
import com.example.dashboardback.admin.dto.AdminDto;
import com.example.dashboardback.global.dto.ResponseDto;
import com.example.dashboardback.admin.dto.AdminDto.LoginResponse;
import com.example.dashboardback.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
@Api(tags = "Admin API")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> singupUser(@Valid @ModelAttribute AdminDto.SignupRequest signupRequest) {
        this.adminService.singup(signupRequest);
        return ResponseEntity.ok(ResponseDto.create(AdminConstants.EBoardResponseMessage.SIGNUP_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "로그인", notes = "로그인을 합니다.")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponse>> loginUser(@Valid @ModelAttribute AdminDto.LoginRequest loginRequest) {
        LoginResponse loginResponse = this.adminService.login(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(AdminConstants.EBoardResponseMessage.LOGIN_SUCCESS.getMessage(),loginResponse));
    }

    @ApiOperation(value = "관리자 정보", notes = "관리자 정보 및 활동중인 관리자 정보를 보여줍니다.")
    @GetMapping("/info")
    public ResponseEntity<ResponseDto<AdminDto.UserInfoResponse>> getUserInfo(){
        return ResponseEntity.ok(ResponseDto.create(AdminConstants.EBoardResponseMessage. GETUSERINFO_SUCCESS.getMessage(),this.adminService.getUserInfo()));
    }


    @ApiOperation(value="로그아웃", notes = "유저를 로그아웃 시킵니다.")
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logoutUser(){
        this.adminService.logout();
        return ResponseEntity.ok(ResponseDto.create(AdminConstants.EBoardResponseMessage.LOGOUT_SUCCESS.getMessage()));
    }

    @ApiOperation(value="전체 로그아웃", notes = "모든 유저를 로그아웃 시킵니다.")
    @PostMapping("/logoutAll")
    public ResponseEntity<ResponseDto> logoutUserAll(){
        this.adminService.logoutAll();
        return ResponseEntity.ok(ResponseDto.create(AdminConstants.EBoardResponseMessage.LOGOUT_SUCCESS.getMessage()));
    }

}
