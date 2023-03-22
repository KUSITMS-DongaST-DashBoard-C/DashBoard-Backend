package com.example.dashboardback.contents.live.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLiveOrderByViewNumRes {
    private Long totalViewNum;
    private List<GetLiveOrderByViewNum> getLiveOrderByViewNumList;
}
