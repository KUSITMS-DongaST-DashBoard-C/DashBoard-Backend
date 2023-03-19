package com.example.dashboardback.loginhistory.controller;

import com.example.dashboardback.loginhistory.service.LoginHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("logintime")
@Api(tags = "LoginHistory API")
@Slf4j
public class LoginHistoryController {
    private final LoginHistoryService loginHistoryService;

    @ApiOperation(value = "로그인한 시간 더미데이터 생성", notes = "로그인한 시간 더미데이터 생성")
    @GetMapping("/dummy")
    public void makeUser() {
        this.loginHistoryService.createLoginTime();
    }

}
