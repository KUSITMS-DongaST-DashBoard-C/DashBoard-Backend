package com.example.dashboardback.chart.service;

import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.au.AuDto.DauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.MauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.WauInfoResponse;
import com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;
import com.example.dashboardback.chart.dto.au.DauDto;

import java.util.List;

public interface ChartService {
    List<GetMajorNumRes> getMajorNum();
    List<DauInfoResponse> getDAU();
    List<MauInfoResponse> getMAU();
    List<WauInfoResponse> getWAU();
}
