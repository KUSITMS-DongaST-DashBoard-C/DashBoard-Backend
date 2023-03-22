package com.example.dashboardback.contents.live.service;

import com.example.dashboardback.contents.live.dto.Res.GetExpectedUploadRes;

import java.util.List;

public interface LiveService {
    List<GetExpectedUploadRes> getExpectedUpload();
}
