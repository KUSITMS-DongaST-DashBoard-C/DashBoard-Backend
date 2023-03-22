package com.example.dashboardback.contents.original.service;

import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;

import java.util.List;

public interface OriginalService {

    void createOriginal();

    List<UploadInfoResponse> getUpload();
}
