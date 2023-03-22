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
public class GetUploadedRes {
    private String title;
    private String major;
    private String thumbnailUrl;
    private Date uploadedTime;
}
