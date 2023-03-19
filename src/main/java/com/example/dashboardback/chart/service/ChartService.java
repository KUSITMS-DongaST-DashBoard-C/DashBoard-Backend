package com.example.dashboardback.chart.service;

import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.au.DauDto;

import java.util.List;

public interface ChartService {
    List<GetMajorNumRes> getMajorNum();
    List<DauDto> getDAU();
}
