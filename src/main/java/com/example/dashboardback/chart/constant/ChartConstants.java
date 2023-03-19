package com.example.dashboardback.chart.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ChartConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EBoardResponseMessage{
        GETMAJOIRNUM_SUCCESS("GET MAJOIRNUM SUCCESS");
        private final String message;
    }

}
