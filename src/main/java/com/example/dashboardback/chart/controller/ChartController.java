package com.example.dashboardback.chart.controller;


import com.example.dashboardback.admin.constant.AdminConstants;
import com.example.dashboardback.admin.dto.AdminDto;
import com.example.dashboardback.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("chart")
@Api(tags = "Chart API")
@Slf4j
public class ChartController {

//    @ApiOperation(value = "진료과별 유저 수", notes = "진료과별 유저 수를 보여줍니다.")
//    @GetMapping("/major")


}
