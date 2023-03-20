package com.example.dashboardback.chart.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ChartConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EBoardResponseMessage{
        GETDATA_SUCCESS("데이터 응답 성공");
        private final String message;
    }

}
