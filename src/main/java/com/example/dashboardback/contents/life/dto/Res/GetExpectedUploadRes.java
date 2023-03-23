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
public class GetExpectedUploadRes {
    private String title;
    private String category;
    private Date expectedUploadTime;
}
