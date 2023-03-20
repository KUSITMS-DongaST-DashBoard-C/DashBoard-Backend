package com.example.dashboardback.chart.dto.au;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public abstract class AuDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "dau, 일일 신규 가입자")
    public static class DauInfoResponse{
        private int daysBefore;
        private long dau;
        private long signupNum;

        public static DauInfoResponse from(int daysBefore, long dau, long signupNum){
            return DauInfoResponse.builder()
                    .daysBefore(daysBefore)
                    .dau(dau)
                    .signupNum(signupNum)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "mau, 월간 신규 가입자")
    public static class MauInfoResponse{
        private int monthsBefore;
        private long mau;
        private long signupNum;

        public static MauInfoResponse from(int monthsBefore, long mau, long signupNum){
            return MauInfoResponse.builder()
                    .monthsBefore(monthsBefore)
                    .mau(mau)
                    .signupNum(signupNum)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "wau, 주간 신규 가입자")
    public static class WauInfoResponse{
        private int weeksNum;
        private long wau;
        private long signupNum;

        public static WauInfoResponse from(int weeksNum, long wau, long signupNum){
            return WauInfoResponse.builder()
                    .weeksNum(weeksNum)
                    .wau(wau)
                    .signupNum(signupNum)
                    .build();
        }
    }
}
