package com.example.dashboardback.contents.live.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLiveOrderByViewNum {
    private String title;
    private String thumbnailUrl;
    private Date liveDate;
    private Long viewNum;
    private Long applicableNum; //신청가능인원
    private Long applicantNum; //신청인원
}
