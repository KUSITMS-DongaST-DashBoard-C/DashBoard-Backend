package com.example.dashboardback.contents.original.repository;


import com.example.dashboardback.contents.original.dto.OriginalDto;
import com.example.dashboardback.contents.original.dto.OriginalDto.DetailInfoRequest;
import com.example.dashboardback.contents.original.dto.OriginalDto.DetailInfoResponse;
import com.example.dashboardback.contents.original.dto.OriginalDto.UploadInfoResponse;

import java.util.List;

public interface OriginalRepositoryCustom {
    List<UploadInfoResponse> getUpload();
    List<DetailInfoResponse> getOrderByHitsDesc(DetailInfoRequest detailInfoRequest);
    List<DetailInfoResponse> getOrderByHitsAsc(DetailInfoRequest detailInfoRequest);
    List<DetailInfoResponse> getOrderByReplyDesc(DetailInfoRequest detailInfoRequest);
    List<DetailInfoResponse> getOrderByLikesDesc(DetailInfoRequest detailInfoRequest);
    List<DetailInfoResponse> getOrderByReviewDesc (DetailInfoRequest detailInfoRequest);
}
