package com.example.dashboardback.chart.dto.Res.city;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GetCityNumRes {

    private String city;
    private Long num;
}
