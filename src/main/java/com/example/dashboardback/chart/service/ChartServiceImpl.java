package com.example.dashboardback.chart.service;


import com.example.dashboardback.chart.dto.Res.GetDailyData;
import com.example.dashboardback.chart.dto.Res.GetMajorNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityNumRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityRatioRes;
import com.example.dashboardback.chart.dto.Res.city.GetCityTrafficRes;
import com.example.dashboardback.chart.dto.au.AuDto;
import com.example.dashboardback.chart.dto.au.AuDto.DauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.MauInfoResponse;
import com.example.dashboardback.chart.dto.au.AuDto.WauInfoResponse;
import com.example.dashboardback.chart.dto.contents.ContentsDto;
import com.example.dashboardback.chart.dto.contents.ContentsDto.GetContentsRatio;
import com.example.dashboardback.chart.repository.ChartRepository;
import com.example.dashboardback.contents.original.repository.OriginalRepository;
import com.example.dashboardback.contents.vod.repository.VodRepository;
import com.example.dashboardback.loginhistory.repository.LoginHistoryRepository;
import com.example.dashboardback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChartServiceImpl implements ChartService {

    private final ChartRepository chartRepository;

    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;
    private final OriginalRepository originalRepository;
    private final VodRepository vodRepository;

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
                    .ratio(((getCityNum.getNum()*100L/userNum)))
                    .build();

            getCityRatioResList.add(getCityRatioRes);
        }

         return GetCityTrafficRes.builder()
                .getCityRatioResList(getCityRatioResList)
                .getCityDailyVisitorsResList(chartRepository.getVisiotrsCnt())
                .getCityNewMemberResList(chartRepository.getNewMemCnt())
                .build();
    }

    @Override
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
    public List<WauInfoResponse> getWAU() {
        List<WauInfoResponse> wauDtos= new ArrayList<>();
        LocalDateTime now = LocalDateTime.of(2023, 3, 28, 17,00);
        for(int i=0;i<7;i++){
            wauDtos.add(WauInfoResponse.from(i,
                    loginHistoryRepository.getWauByWeek(now.minusWeeks(i)),
                    userRepository.getSignUpNumByWeek(now.minusWeeks(i))));
        }
        return wauDtos;
    }

    @Override
    public String getYDA() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷

        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DATE, -1);

        String strYDA = sdf.format(c1.getTime());
        return strYDA;
    }

    @Override
    public String getTDA() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();

        String strToday = sdf.format(c1.getTime());

        return strToday;
    }


    @Override
    public GetDailyData.GetDailyDataRes getDailyData() {
        String yda = getYDA();
        String tda = getTDA();
        //(1)
        Long visitorCnt = chartRepository.getVisitorCnt(tda);
        Long YDAvisitorCnt = chartRepository.getVisitorCnt(yda);
        GetDailyData.VisitorData visitorData = GetDailyData.VisitorData.from(visitorCnt,((visitorCnt-YDAvisitorCnt)*100)/YDAvisitorCnt);
        //(2)
        GetDailyData.PageViewData pageViewData = GetDailyData.PageViewData.from(1536L,8L);
        //(3)
        Long newMemberCnt = chartRepository.getNewMemberCnt(tda);
        Long YDANewMemberCnt = chartRepository.getNewMemberCnt(yda);
        GetDailyData.NewMemberData newMemberData = GetDailyData.NewMemberData.from(newMemberCnt,((newMemberCnt-YDANewMemberCnt)*100)/YDANewMemberCnt);
        //(4)
        Long bounceCnt = chartRepository.getBounceCnt();
        Long totalUser = chartRepository.getUserNum();
        Long YDABounceCnt = chartRepository.getYDABounceCnt();
        GetDailyData.BounceRateData bounceRateData = GetDailyData.BounceRateData.from(bounceCnt*100L/totalUser,((bounceCnt-YDABounceCnt)*100)/YDABounceCnt);

        return GetDailyData.GetDailyDataRes.from(visitorData,pageViewData,newMemberData,bounceRateData);

    }

    @Override
    public List<GetContentsRatio> getContentsRatio() {
        List<GetContentsRatio> contentsRatios=new ArrayList<>();
        Long sum=this.originalRepository.getAllViewNum()+this.vodRepository.getAllViewNum();
        GetContentsRatio original=new GetContentsRatio("original",this.originalRepository.getAllViewNum()*100/sum);
        GetContentsRatio vod=new GetContentsRatio("vod", this.vodRepository.getAllViewNum()*100/sum);

        contentsRatios.add(original);
        contentsRatios.add(vod);
        return contentsRatios;
    }
}
