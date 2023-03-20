package com.example.dashboardback.chart.dto.Res.city;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetCityRatioRes {
        private String city;
        private Long ratio;
}
