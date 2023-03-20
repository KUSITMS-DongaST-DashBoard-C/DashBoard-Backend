package com.example.dashboardback.chart.service;

import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;

import java.util.List;

public interface ChartService {
    List<GetMajorNumRes> getMajorNum();

    List<GetCityTrafficRes> getCityData();
}
