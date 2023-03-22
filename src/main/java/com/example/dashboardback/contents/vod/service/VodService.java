package com.example.dashboardback.contents.vod.service;

import com.example.dashboardback.contents.original.dto.OriginalDto;
import com.example.dashboardback.contents.vod.dto.VodDto;
import com.example.dashboardback.contents.vod.dto.VodDto.DetailInfoRequest;
import com.example.dashboardback.contents.vod.dto.VodDto.DetailInfoResponse;
import com.example.dashboardback.contents.vod.dto.VodDto.UploadInfoResponse;

import javax.validation.Valid;
import java.util.List;

public interface VodService {
    void createVod();

    List<UploadInfoResponse> getUpload();

    List<DetailInfoResponse> getOrderByHitsDesc(DetailInfoRequest detailInfoRequest);

    List<DetailInfoResponse> getOrderByHitsAsc(DetailInfoRequest detailInfoRequest);

    Long getHits(DetailInfoRequest detailInfoRequest);

}
