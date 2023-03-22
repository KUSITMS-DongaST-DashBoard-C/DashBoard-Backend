package com.example.dashboardback.chart.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ChartConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EChartResponseMessage{
        GETDAU_SUCCESS("DAU 데이터 조회를 성공했습니다"),
        GETMAU_SUCCESS("MAU 데이터 조회를 성공했습니다"),
        GETWAU_SUCCESS("WAU 데이터 조회를 성공했습니다"),
        GETCONTENTS_SUCCESS("콘텐츠별 유입률 조회를 성공했습니다"),
        GETDATA_SUCCESS("데이터 응답 성공");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum DAU_WhenList{
        now("오늘"),
        one("1일 전"),
        two("2일 전"),
        three("3일 전"),
        four("4일 전"),
        five("5일 전"),
        six("6일 전");

        private final String message;
    }
}
