package com.example.dashboardback.contents.life.service;

import com.example.dashboardback.contents.life.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.life.dto.Res.GetFilteredLifeRes;
import com.example.dashboardback.contents.life.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.live.dto.Req.DateReq;

import java.util.List;

public interface LifeService {
    List<GetExpectedUploadRes> getExpectedUpload();
    List<GetUploadedRes> getUploaded();

    GetFilteredLifeRes getLifeOrderByViewNum(DateReq dateReq, String orderBy);

    GetFilteredLifeRes getLifeOrderByCommentNum(DateReq dateReq);
}
