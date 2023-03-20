package com.example.dashboardback.chart.controller;


import com.example.dashboardback.chart.constant.ChartConstants;
import com.example.dashboardback.chart.constant.ChartConstants.EChartResponseMessage;
import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.au.AuDto.DauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.MauInfoResponse;
import com.example.dashboardback.chart.service.ChartService;
import com.example.dashboardback.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("chart")
@Api(tags = "Chart API")
@Slf4j
public class ChartController {

    private final ChartService chartService;

    @ApiOperation(value = "진료과별 유저 수", notes = "진료과별 유저 수를 보여줍니다.")
    @GetMapping("/major")
    public ResponseEntity<ResponseDto<List<GetMajorNumRes>>> getMajorNum(){
        return ResponseEntity.ok(ResponseDto.create(EChartResponseMessage.GETDATA_SUCCESS.getMessage(),this.chartService.getMajorNum()));
    }

//    @ApiOperation(value="시/도별 회원수", notes="시/도별 회원수의 비율을 보여줍니다.")
//    @GetMapping("/city")
//    public ResponseEntity<ResponseDto<List<GetCityTrafficRes>>> getCityNum() {
//        return ResponseEntity.ok(ResponseDto.create(ChartConstants.EBoardResponseMessage.GETDATA_SUCCESS.getMessage(),this.chartService.getCityData()));
//    }

    @ApiOperation(value = "DAU 데이터 조회", notes = "DAU 데이터를 조회합니다.")
    @GetMapping("/dau")
    public ResponseEntity<ResponseDto<List<DauInfoResponse>>> getDAU(){
        return ResponseEntity.ok(ResponseDto.create(EChartResponseMessage.GETDAU_SUCCESS.getMessage(),
                this.chartService.getDAU()));
    }

    @ApiOperation(value = "MAU 데이터 조회", notes = "MAU 데이터를 조회합니다.")
    @GetMapping("/mau")
    public ResponseEntity<ResponseDto<List<MauInfoResponse>>> getMAU(){
        return ResponseEntity.ok(ResponseDto.create(EChartResponseMessage.GETMAU_SUCCESS.getMessage(),
                this.chartService.getMAU()));
    }

}
