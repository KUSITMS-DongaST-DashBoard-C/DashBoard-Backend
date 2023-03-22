package com.example.dashboardback.contents.vod.repository;

import com.example.dashboardback.contents.vod.dto.VodDto.DetailInfoRequest;
import com.example.dashboardback.contents.vod.dto.VodDto.DetailInfoResponse;
import com.example.dashboardback.contents.vod.dto.VodDto.UploadInfoResponse;

import java.util.List;

public interface VodRepositoryCustom {
    List<UploadInfoResponse> getUpload();
    List<DetailInfoResponse> getOrderByHitsDesc(DetailInfoRequest detailInfoRequest);
    List<DetailInfoResponse> getOrderByHitsAsc(DetailInfoRequest detailInfoRequest);
}
