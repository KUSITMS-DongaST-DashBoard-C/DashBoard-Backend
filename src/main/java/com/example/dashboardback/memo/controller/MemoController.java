package com.example.dashboardback.memo.controller;

import com.example.dashboardback.global.dto.ResponseDto;
import com.example.dashboardback.memo.constant.MemoConstants.EBoardResponseMessage;
import com.example.dashboardback.memo.constant.MemoConstants.EMemoController;
import com.example.dashboardback.memo.dto.MemoDto.*;
import com.example.dashboardback.memo.service.MemoService;
import com.example.dashboardback.admin.dto.PaginationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.dashboardback.memo.constant.MemoConstants.EBoardResponseMessage.CREATE_MEMO_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("memo")
@Api(tags = "Memo API")
public class MemoController {
    private final MemoService memoService;

    @ApiOperation(value="메모 생성", notes="메모를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateResponse>> createMemo(@Valid @ModelAttribute CreateRequest createRequest){
        CreateResponse createResponse=this.memoService.createMemo(createRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(EMemoController.LOCATION_ID_PATH.getValue())
                .buildAndExpand(createResponse.getMemoId())
                .toUri();
        return ResponseEntity.created(location).body(ResponseDto.create(CREATE_MEMO_SUCCESS.getMessage(),createResponse));
    }

    @ApiOperation(value = "메모 수정", notes = "메모를 수정합니다.")
    @PostMapping("/update")
    public ResponseEntity<ResponseDto<UpdateResponse>> updateMemo(@Valid @ModelAttribute UpdateRequest updateRequest){
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.UPDATE_MEMO_SUCCESS.getMessage(), this.memoService.updateMemo(updateRequest)));
    }

    @ApiOperation(value = "메모 삭제", notes = "메모를 삭제합니다.")
    @DeleteMapping("/{memoId}")
    public ResponseEntity<ResponseDto> deleteMemo(@PathVariable Long memoId){
        this.memoService.deleteMemo(memoId);
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.DELETE_MEMO_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "메모 작성 시간순 조회", notes = "메모를 작성 시간순으로 조회합니다.")
    @GetMapping
    public ResponseEntity<ResponseDto<PaginationDto<List<GetAllResponse>>>> getAllMemos(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(ResponseDto.create(EBoardResponseMessage.GET_ALL_DETAIL_MEMO_SUCCESS.getMessage(), this.memoService.getAllMemos(pageable)));
    }
}
