package com.example.dashboardback.user.controller;


import com.example.dashboardback.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Api(tags = "User API")
@Slf4j
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "유저더미데이터생성", notes = "유저더미데이터 생성")
    @GetMapping("/dummy")
    public void makeUser() {
        userService.makeUserData();
    }

    @ApiOperation(value = "유저더미데이터 생성일, 방문일 수정", notes = "유저더미데이터 생성일, 방문일 수정")
    @GetMapping("/dummy/update")
    public void updateUser() {
        userService.updateCreatedAtWithRandomDate(200, 7);
    }
}
