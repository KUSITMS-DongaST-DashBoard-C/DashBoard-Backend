package com.example.dashboardback.chart.service;


import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;

import com.example.dashboardback.chart.dto.Res.city.GetCityNewMemberRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityRatioRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;
import com.example.dashboardback.chart.dto.au.DauDto;
import com.example.dashboardback.chart.dto.au.AuDto;
import com.example.dashboardback.chart.dto.au.AuDto.DauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.MauInfoResponse;
import com.example.dashboardback.chart.repository.ChartRepository;
import com.example.dashboardback.loginhistory.repository.LoginHistoryRepository;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public GetCityTrafficRes getCityData() {

        Long userNum = chartRepository.getUserNum();
        List<GetCityRatioRes> getCityRatioResList=new ArrayList<>();
        List<GetCityNumRes> getCityNums=this.chartRepository.getCityNum();

        for(GetCityNumRes getCityNum: getCityNums){

            GetCityRatioRes getCityRatioRes = GetCityRatioRes.builder()
                    .city(getCityNum.getCity())
                    .ratio((long) ((getCityNum.getNum()*100.0/userNum)))
                    .build();

            getCityRatioResList.add(getCityRatioRes);
        }

         return GetCityTrafficRes.builder()
                .getCityRatioResList(getCityRatioResList)
                .getCityDailyVisitorsResList(chartRepository.getVisiotrsCnt())
                .getCityNewMemberResList(chartRepository.getNewMemCnt())
                .build();
    }

    public List<DauDto> getDAU() {
        List<DauDto> dauDtos=new ArrayList<>();
        LocalDateTime now = LocalDateTime.of(2023,3,28,00,00);
        for(int i=0;i<7;i++){
            DauDto dauDto=new DauDto();
            dauDto.setDaysBefore(i);
            dauDto.setDau(loginHistoryRepository.getDauByDay(i));
            dauDto.setSignupNum(userRepository.getSignUpNumByDay(i));
            dauDtos.add(dauDto);
    public List<DauInfoResponse> getDAU() {
        List<DauInfoResponse> dauDtos = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dauDtos.add(DauInfoResponse.from(i,
                    loginHistoryRepository.getDauByDay(i),
                    userRepository.getSignUpNumByDay(i)));
        }
        return dauDtos;
    }


    @Override
    public List<MauInfoResponse> getMAU() {
        List<MauInfoResponse> mauDtos = new ArrayList<>();
        LocalDate now = LocalDate.of(2023, 3, 28);
        for (int i = 0; i < 7; i++) {
            mauDtos.add(MauInfoResponse.from(i,
                    loginHistoryRepository.getMauByMonth(
                            now.minusMonths(i).getYear(),
                            now.minusMonths(i).getMonthValue()),
                    userRepository.getSignUpNumByMonth(
                            now.minusMonths(i).getYear(),
                            now.minusMonths(i).getMonthValue())));
        }
        return mauDtos;
    }

    @Override
    public List<AuDto.WauInfoResponse> getWAU() {
        return null;
    }
}
