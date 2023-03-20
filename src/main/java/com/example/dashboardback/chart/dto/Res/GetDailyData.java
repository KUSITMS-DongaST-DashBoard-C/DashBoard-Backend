package com.example.dashboardback.chart.dto.Res;

import io.swagger.annotations.ApiModel;
import lombok.*;


public abstract class GetDailyData {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "일일 데이터 전달")
    public static class GetDailyDataRes {
        private VisitorData visitorData;
        private PageViewData pageViewData;
        private NewMemberData newMemberData;
        private BounceRateData bounceRateData;

        public static GetDailyDataRes from(VisitorData vData,PageViewData pData,
                                           NewMemberData nData, BounceRateData bData){
            return GetDailyDataRes.builder()
                    .visitorData(vData)
                    .newMemberData(nData)
                    .pageViewData(pData)
                    .bounceRateData(bData)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "방문자수 데이터")
    public static class VisitorData {
        private Long visitorNum;
        private Long changRate;

        public static VisitorData from(Long num,Long rate){
            return VisitorData.builder()
                    .visitorNum(num)
                    .changRate(rate)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "페이지뷰 데이터")
    public static class PageViewData {
        private Long pageViewNum;
        private Long changRate;

        public static PageViewData from(Long num,Long rate){
            return PageViewData.builder()
                    .pageViewNum(num)
                    .changRate(rate)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "가입자수 데이터")
    public static class NewMemberData {
        private Long newMemberNum;
        private Long changRate;


        public static NewMemberData from(Long num,Long rate){
            return NewMemberData.builder()
                    .newMemberNum(num)
                    .changRate(rate)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "이탈율 데이터")
    public static class BounceRateData {
        private Long bounceRate;
        private Long changRate;

        public static BounceRateData from(Long num,Long rate){
            return BounceRateData.builder()
                    .bounceRate(num)
                    .changRate(rate)
                    .build();
        }
    }

}
