package com.example.dashboardback.chart.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMajorNumRes {

    private String majorName;

    private Long majorNum;

    public static GetMajorNumRes response(String name, Long num){
        return GetMajorNumRes.builder()
                .majorName(name)
                .majorNum(num)
                .build();
    }
}
