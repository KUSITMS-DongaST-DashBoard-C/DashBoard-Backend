package com.example.dashboardback.contents.live.controller;

import com.example.dashboardback.contents.live.constant.LiveConstants;
import com.example.dashboardback.contents.live.dto.Res.GetExpectedUploadRes;
import com.example.dashboardback.contents.live.dto.Res.GetUploadedRes;
import com.example.dashboardback.contents.live.service.LiveService;
import com.example.dashboardback.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/live")
@Api(tags = "Live API")
public class LiveController {
private final LiveService liveService;
    @ApiOperation(value = "업로드 예정 live", notes = "업로드 예정 live 데이터를 보여줍니다.")
    @GetMapping("/expectedUpload")
    public ResponseEntity<ResponseDto<List<GetExpectedUploadRes>>> getExpectedUpload(){
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage.GET_EXPECTEDUPLOAD_SUCCESS.getMessage(), this.liveService.getExpectedUpload()));
    }

    @ApiOperation(value = "업로드 예정 live", notes = "업로드 예정 live 데이터를 보여줍니다.")
    @GetMapping("/uploaded")
    public ResponseEntity<ResponseDto<List<GetUploadedRes>>> getUploaded(){
        return ResponseEntity.ok(ResponseDto.create(LiveConstants.EChartResponseMessage.GET_UPLOADED_SUCCESS.getMessage(), this.liveService.getUploaded()));
    }
}
