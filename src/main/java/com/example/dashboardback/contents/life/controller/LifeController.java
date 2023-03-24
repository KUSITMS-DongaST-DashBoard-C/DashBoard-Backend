package com.example.dashboardback.contents.life.controller;

import com.example.dashboardback.contents.life.constant.LifeConstants;
import com.example.dashboardback.contents.life.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.life.dto.Res.GetFilteredLifeRes;
import com.example.dashboardback.contents.life.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.life.service.LifeService;
import com.example.dashboardback.contents.live.constant.LiveConstants;
import com.example.dashboardback.contents.live.dto.Req.DateReq;
import com.example.dashboardback.contents.live.dto.Res.GetFilteredLiveRes;
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
@RequestMapping("/life")
@Slf4j
@Api(tags = "Life API")
public class LifeController {

    private final LifeService lifeService;

    @ApiOperation(value = "업로드 예정 life", notes = "업로드 예정 life 데이터를 보여줍니다.")
    @GetMapping("/expectedUpload")
    public ResponseEntity<ResponseDto<List<GetExpectedUploadRes>>> getExpectedUpload(){
        return ResponseEntity.ok(ResponseDto.create(LifeConstants.EChartResponseMessage.GET_EXPECTEDUPLOAD_SUCCESS.getMessage(),this.lifeService.getExpectedUpload()));
    }


    @ApiOperation(value = "업로드 완료 life", notes = "업로드 완료된 life 데이터를 보여줍니다.")
    @GetMapping("/uploaded")
    public ResponseEntity<ResponseDto<List<GetUploadedRes>>> getUploaded(){
        return ResponseEntity.ok(ResponseDto.create(LifeConstants.EChartResponseMessage.GET_UPLOADED_SUCCESS.getMessage(), this.lifeService.getUploaded()));
    }

    @ApiOperation(value = "Life 세부 컨텐츠-조회수에 따른 정렬", notes = "조회수 정렬에 따른 세부 컨텐츠를 보여줍니다.")
    @GetMapping("/detail/view/{orderBy}")
    public ResponseEntity<ResponseDto<GetFilteredLifeRes>> getOrderByViewNum(
            @PathVariable("orderBy") String orderBy, //asc: 오름차순(조회수 낮은순), desc:내림차순(조회수 높은순)
            @Valid @ModelAttribute DateReq dateReq){
        log.info(dateReq.getStartDate() +" ");
        log.info(orderBy +" ");
        GetFilteredLifeRes getFilteredLifeRes = this.lifeService.getLifeOrderByViewNum(dateReq, orderBy);
        return ResponseEntity.ok(ResponseDto.create(LifeConstants.EChartResponseMessage.GET_LIFEORDERBYVIEWNUM_SUCCESS.getMessage(), getFilteredLifeRes));
    }

    @ApiOperation(value = "Life 세부 컨텐츠-댓글 수에 따른 정렬", notes = "댓글 수에 따른 세부 컨텐츠를 보여줍니다.")
    @GetMapping("/detail/comment")
    public ResponseEntity<ResponseDto<GetFilteredLifeRes>> getOrderByComment(
            @Valid@ModelAttribute DateReq dateReq){
        GetFilteredLifeRes getFilteredLifeRes = this.lifeService.getLifeOrderByCommentNum(dateReq);
        return ResponseEntity.ok(ResponseDto.create(LifeConstants.EChartResponseMessage.GET_LIFEORDERBYCOMMENTNUM_SUCCESS.getMessage(), getFilteredLifeRes));
    }
}
