package com.example.dashboardback.chart.dto.contents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public abstract class ContentsDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GetContentsRatio {

        private String contents;
        private Long ratio;
    }

}
