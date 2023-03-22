package com.example.dashboardback.contents.life.service;

import com.example.dashboardback.contents.life.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.life.dto.Res.GetUploadedRes;

import java.util.List;

public interface LifeService {
    List<GetExpectedUploadRes> getExpectedUpload();
    List<GetUploadedRes> getUploaded();
}
