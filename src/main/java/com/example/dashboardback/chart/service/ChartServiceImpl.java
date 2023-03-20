package com.example.dashboardback.chart.service;


import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;
import com.example.dashboardback.chart.repository.ChartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChartServiceImpl implements ChartService {

    private final ChartRepository chartRepository;

    @Override
    public List<GetMajorNumRes> getMajorNum() {
       return chartRepository.getMajorNum();
    }

    @Override
    public List<GetCityTrafficRes> getCityData() {
//        GetCityTrafficRes.builder()
//                .getCityNewMemberResList(chartRepository.getNewMemCnt())
//                .build();
        return null;
    }

}
