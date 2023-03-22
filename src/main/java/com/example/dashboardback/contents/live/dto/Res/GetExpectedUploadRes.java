package com.example.dashboardback.contents.live.dto.Res;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetExpectedUploadRes {
    private String title;
    private String major;
    private String thumbnailUrl;
    private Date expectedUploadTime;
}
