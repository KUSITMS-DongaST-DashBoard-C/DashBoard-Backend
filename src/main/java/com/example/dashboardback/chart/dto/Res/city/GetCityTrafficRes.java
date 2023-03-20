package com.example.dashboardback.chart.dto.Res.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetCityTrafficRes {
    List<GetCityRatioRes> getCityRatioResList;
    List<GetCityDailyVisitorsRes> getCityDailyVisitorsResList;
    List<GetCityNewMemberRes> getCityNewMemberResList;
}
