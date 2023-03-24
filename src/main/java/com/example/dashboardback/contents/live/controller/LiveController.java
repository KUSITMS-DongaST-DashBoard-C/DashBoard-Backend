package com.example.dashboardback.contents.live.controller;

import com.example.dashboardback.contents.live.constant.LiveConstants;
import com.example.dashboardback.contents.live.dto.Req.DateReq;
import com.example.dashboardback.contents.live.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.live.dto.Res.GetFilteredLiveRes;
import com.example.dashboardback.contents.live.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.live.service.LiveService;
import com.example.dashboardback.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/live")
@Slf4j
@Api(tags = "Live API")
public class LiveController {
private final LiveService liveService;
    @ApiOperation(value = "업로드 예정 live", notes = "업로드 예정 live 데이터를 보여줍니다.")
    @GetMapping("/expectedUpload")
    public ResponseEntity<ResponseDto<List<GetExpectedUploadRes>>> getExpectedUpload(){
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage.GET_EXPECTEDUPLOAD_SUCCESS.getMessage(), this.liveService.getExpectedUpload()));
    }

    @ApiOperation(value = "업로드 완료 live", notes = "업로드 완료된 live 데이터를 보여줍니다.")
    @GetMapping("/uploaded")
    public ResponseEntity<ResponseDto<List<GetUploadedRes>>> getUploaded(){
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage.GET_UPLOADED_SUCCESS.getMessage(), this.liveService.getUploaded()));
    }

    @ApiOperation(value = "Live 세부 컨텐츠-조회수에 따른 정렬", notes = "조회수 정렬에 따른 세부 컨텐츠를 보여줍니다.")
    @GetMapping("/detail/view/{orderBy}")
    public ResponseEntity<ResponseDto<GetFilteredLiveRes>> getOrderByViewNum(
            @PathVariable("orderBy") String orderBy, //asc: 오름차순(조회수 낮은순), desc:내림차순(조회수 높은순)
            @Valid @ModelAttribute DateReq dateReq){
        GetFilteredLiveRes getFilteredLiveRes = this.liveService.getLiveOrderByViewNum(dateReq, orderBy);
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage. GET_LIVEORDERBYVIEWNUM_SUCCESS.getMessage(), getFilteredLiveRes));
    }

    @ApiOperation(value = "Live 세부 컨텐츠-신청인원수에 따른 정렬", notes = "신청인원 수에 따른 세부 컨텐츠를 보여줍니다.")
    @GetMapping("/detail/applicant")
    public ResponseEntity<ResponseDto<GetFilteredLiveRes>> getOrderByApplicant(
            @Valid@ModelAttribute DateReq dateReq){
        GetFilteredLiveRes getFilteredLiveRes = this.liveService.getLiveOrderByApplicantNum(dateReq);
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage. GET_LIVEORDERBYVIEWNUM_SUCCESS.getMessage(), getFilteredLiveRes));
    }

    @ApiOperation(value = "Live 세부 컨텐츠-댓글 수에 따른 정렬", notes = "댓글 수에 따른 세부 컨텐츠를 보여줍니다.")
    @GetMapping("/detail/comment")
    public ResponseEntity<ResponseDto<GetFilteredLiveRes>> getOrderByComment(
            @Valid@ModelAttribute DateReq dateReq){
        GetFilteredLiveRes getFilteredLiveRes = this.liveService.getLiveOrderByCommentNum(dateReq);
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage. GET_LIVEORDERBYVIEWNUM_SUCCESS.getMessage(), getFilteredLiveRes));
    }
}
