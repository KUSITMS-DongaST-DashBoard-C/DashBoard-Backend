package com.example.dashboardback.contents.original.service;

import com.example.dashboardback.contents.original.dto.OrigianlDto;
import com.example.dashboardback.contents.original.dto.OrigianlDto.DetailInfoRequest;
import com.example.dashboardback.contents.original.dto.OrigianlDto.DetailInfoResponse;
import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;

import java.util.List;

public interface OriginalService {

    void createOriginal();

    List<UploadInfoResponse> getUpload();

    List<DetailInfoResponse> getOrderByHitsDesc(DetailInfoRequest detailInfoRequest);

    List<DetailInfoResponse> getOrderByHitsAsc(DetailInfoRequest detailInfoRequest);

    List<DetailInfoResponse> getOrderByReplyDesc(DetailInfoRequest detailInfoRequest);

    List<DetailInfoResponse> getOrderByLikesDesc(DetailInfoRequest detailInfoRequest);

    List<DetailInfoResponse> getOrderByReviewDesc (DetailInfoRequest detailInfoRequest);
}
