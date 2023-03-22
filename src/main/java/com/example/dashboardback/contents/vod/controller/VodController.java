package com.example.dashboardback.contents.vod.controller;

import com.example.dashboardback.contents.original.constant.OriginalConstants;
import com.example.dashboardback.contents.vod.constant.VodConstants;
import com.example.dashboardback.contents.vod.constant.VodConstants.EVodResponseMessage;
import com.example.dashboardback.contents.vod.dto.VodDto;
import com.example.dashboardback.contents.vod.dto.VodDto.DetailInfoRequest;
import com.example.dashboardback.contents.vod.dto.VodDto.DetailInfoResponse;
import com.example.dashboardback.contents.vod.dto.VodDto.UploadInfoResponse;
import com.example.dashboardback.contents.vod.service.VodService;
import com.example.dashboardback.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("vod")
@Api(tags = "VOD API")
@Slf4j
public class VodController {

    private final VodService vodService;

    @ApiOperation(value = "Vod더미데이터생성", notes = "Vod더미데이터 생성")
    @GetMapping("/dummy")
    public void makeVod() {
        vodService.createVod();
    }

    @ApiOperation(value = "업로드 예정 영상 콘텐츠 보기", notes = "업로드 예정 영상 콘텐츠 보기")
    @GetMapping("/expectedUpload")
    public ResponseEntity<ResponseDto<List<UploadInfoResponse>>> getExpectedUpload(){
        return ResponseEntity.ok(ResponseDto.create(EVodResponseMessage.GETUPLOAD_SUCCESS.getMessage(),
                this.vodService.getUpload()));
    }

    @ApiOperation(value="세부 콘텐츠 분석 조회수 많은 순으로 정렬", notes="세부 콘텐츠 분석 조회수 많은 순으로 정렬")
    @GetMapping("/orderByHitsDesc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByHitsDesc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(EVodResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.vodService.getOrderByHitsDesc(detailInfoRequest)));
    }

    @ApiOperation(value="세부 콘텐츠 분석 조회수 적은 순으로 정렬", notes="세부 콘텐츠 분석 조회수 적은 순으로 정렬")
    @GetMapping("/orderByHitsAsc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByHitsAsc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(EVodResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.vodService.getOrderByHitsAsc(detailInfoRequest)));
    }
}
