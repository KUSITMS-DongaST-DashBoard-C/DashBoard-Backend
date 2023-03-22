package com.example.dashboardback.contents.original.repository;

import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;

import java.util.List;

public interface OriginalRepositoryCustom {
    List<UploadInfoResponse> getUpload();
}
