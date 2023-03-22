package com.example.dashboardback.contents.life.controller;

import com.example.dashboardback.contents.life.constant.LifeConstants;
import com.example.dashboardback.contents.life.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.life.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.life.service.LifeService;
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
}
