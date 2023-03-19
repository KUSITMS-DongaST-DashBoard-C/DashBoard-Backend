package com.example.dashboardback.chart.dto.au;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DauDto {

    private int daysBefore;
    private long dau;
    private long signupNum;

}
