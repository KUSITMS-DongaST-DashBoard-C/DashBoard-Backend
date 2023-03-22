package com.example.dashboardback.contents.live.service;

import com.example.dashboardback.contents.live.dto.Req.DateReq;
import com.example.dashboardback.contents.live.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.live.dto.Res.GetFilteredLiveRes;
import com.example.dashboardback.contents.live.dto.Res.GetUploadedRes;

import java.util.List;

public interface LiveService {
    List<GetExpectedUploadRes> getExpectedUpload();
    List<GetUploadedRes> getUploaded();
    GetFilteredLiveRes getLiveOrderByViewNum(DateReq dateReq, String orderBy);
    GetFilteredLiveRes getLiveOrderByApplicantNum(DateReq dateReq);

}
