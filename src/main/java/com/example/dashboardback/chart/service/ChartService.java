package com.example.dashboardback.chart.service;

import com.example.dashboardback.chart.dto.Res.GetDailyData;
import com.example.dashboardback.chart.dto.Res.GetDailyData.GetDailyDataRes;
import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;
import com.example.dashboardback.chart.dto.au.AuDto.DauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.MauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.WauInfoResponse;
import com.example.dashboardback.chart.dto.contents.ContentsDto;
import com.example.dashboardback.chart.dto.contents.ContentsDto.GetContentsRatio;

import java.util.List;

public interface ChartService {
    List<GetMajorNumRes> getMajorNum();
    GetCityTrafficRes getCityData();
    List<DauInfoResponse> getDAU();
    List<MauInfoResponse> getMAU();
    List<WauInfoResponse> getWAU();

    String getYDA();
    String getTDA();
    GetDailyDataRes getDailyData();
    List<GetContentsRatio> getContentsRatio();
}
