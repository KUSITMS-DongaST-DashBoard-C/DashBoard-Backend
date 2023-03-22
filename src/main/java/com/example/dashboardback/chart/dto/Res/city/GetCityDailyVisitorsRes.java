package com.example.dashboardback.chart.dto.Res.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCityDailyVisitorsRes {

    private String city;
    private Long visitorCnt;

}
