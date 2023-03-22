package com.example.dashboardback.contents.original.controller;

import com.example.dashboardback.contents.original.constant.OriginalConstants;
import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;
import com.example.dashboardback.contents.original.service.OriginalService;
import com.example.dashboardback.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("original")
@Api(tags = "Original API")
@Slf4j
public class OriginalController {

    private final OriginalService originalService;

    @ApiOperation(value = "Original더미데이터생성", notes = "Origianl더미데이터 생성")
    @GetMapping("/dummy")
    public void makeUser() {
        originalService.createOriginal();
    }

    @ApiOperation(value = "업로드 예정 영상 콘텐츠 보기", notes = "업로드 예정 영상 콘텐츠 보기")
    @GetMapping("/expectedUpload")
    public ResponseEntity<ResponseDto<List<UploadInfoResponse>>> getWAU(){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETUPLOAD_SUCCESS.getMessage(),
                this.originalService.getUpload()));
    }

}
