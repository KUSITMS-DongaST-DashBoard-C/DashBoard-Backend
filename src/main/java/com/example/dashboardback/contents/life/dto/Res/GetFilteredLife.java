package com.example.dashboardback.contents.life.dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetFilteredLife {

    private String title;
    private String category;
    private String thumbnailUrl;
    private Date uploadDate;
    private Long viewNum;
    private Long commentNum;
    private Long likeNum;
}
