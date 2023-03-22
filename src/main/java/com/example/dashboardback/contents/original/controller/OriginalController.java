package com.example.dashboardback.contents.original.controller;

import com.example.dashboardback.contents.original.constant.OriginalConstants;
import com.example.dashboardback.contents.original.dto.OrigianlDto;
import com.example.dashboardback.contents.original.dto.OrigianlDto.DetailInfoRequest;
import com.example.dashboardback.contents.original.dto.OrigianlDto.DetailInfoResponse;
import com.example.dashboardback.contents.original.dto.OrigianlDto.UploadInfoResponse;
import com.example.dashboardback.contents.original.service.OriginalService;
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
    public ResponseEntity<ResponseDto<List<UploadInfoResponse>>> getExpectedUpload(){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETUPLOAD_SUCCESS.getMessage(),
                this.originalService.getUpload()));
    }

    @ApiOperation(value="세부 콘텐츠 분석 조회수 많은 순으로 정렬", notes="세부 콘텐츠 분석 조회수 많은 순으로 정렬")
    @GetMapping("/orderByHitsDesc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByHitsDesc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.originalService.getOrderByHitsDesc(detailInfoRequest)));
    }

    @ApiOperation(value="세부 콘텐츠 분석 조회수 적은 순으로 정렬", notes="세부 콘텐츠 분석 조회수 적은 순으로 정렬")
    @GetMapping("/orderByHitsAsc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByHitsAsc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.originalService.getOrderByHitsAsc(detailInfoRequest)));
    }

    @ApiOperation(value="세부 콘텐츠 분석 댓글 많은 순으로 정렬", notes="세부 콘텐츠 분석 댓글 많은 순으로 정렬")
    @GetMapping("/orderByReplyDesc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByReplyDesc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.originalService.getOrderByReplyDesc(detailInfoRequest)));
    }

    @ApiOperation(value="세부 콘텐츠 분석 좋아요 많은 순으로 정렬", notes="세부 콘텐츠 분석 좋아요 많은 순으로 정렬")
    @GetMapping("/orderByLikesDesc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByLikesDesc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.originalService.getOrderByLikesDesc(detailInfoRequest)));
    }

    @ApiOperation(value="세부 콘텐츠 분석 리뷰 많은 순으로 정렬", notes="세부 콘텐츠 분석 리뷰 많은 순으로 정렬")
    @GetMapping("/orderByReviewDesc")
    public ResponseEntity<ResponseDto<List<DetailInfoResponse>>> getOrderByReviewDesc(@Valid @ModelAttribute DetailInfoRequest detailInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(OriginalConstants.EOriginalResponseMessage.GETDETAIL_SUCCESS.getMessage(),
                this.originalService.getOrderByReviewDesc(detailInfoRequest)));
    }

}
