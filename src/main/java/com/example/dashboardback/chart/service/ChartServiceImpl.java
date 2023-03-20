package com.example.dashboardback.chart.service;


import com.example.dashboardback.chart.constant.ChartConstants;
import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;
import com.example.dashboardback.chart.dto.au.DauDto;
import com.example.dashboardback.chart.repository.ChartRepository;
import com.example.dashboardback.loginhistory.repository.LoginHistoryRepository;
import com.example.dashboardback.user.entity.User;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChartServiceImpl implements ChartService {

    private final ChartRepository chartRepository;

    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<GetMajorNumRes> getMajorNum() {
       return chartRepository.getMajorNum();
    }

//    @Override
//    public List<GetCityTrafficRes> getCityData() {
////        GetCityTrafficRes.builder()
////                .getCityNewMemberResList(chartRepository.getNewMemCnt())
////                .build();
//        return null;
//    }
    public List<DauDto> getDAU() {
        List<DauDto> dauDtos=new ArrayList<>();
        LocalDateTime now = LocalDateTime.of(2023,3,28,00,00);
        for(int i=0;i<7;i++){
            DauDto dauDto=new DauDto();
            dauDto.setDaysBefore(i);
            dauDto.setDau(loginHistoryRepository.getDauByDay(i));
            dauDto.setSignupNum(userRepository.getSignUpNumByDay(i));
            dauDtos.add(dauDto);
        }
        return dauDtos;
    }

}
