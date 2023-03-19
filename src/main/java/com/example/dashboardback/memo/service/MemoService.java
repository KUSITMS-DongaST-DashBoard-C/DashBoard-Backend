package com.example.dashboardback.memo.service;

import com.example.dashboardback.memo.dto.MemoDto.*;
import com.example.dashboardback.memo.entity.Memo;
import com.example.dashboardback.admin.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemoService {
    CreateResponse createMemo(CreateRequest createRequest);
    UpdateResponse updateMemo(UpdateRequest updateRequest);
    Memo deleteMemo(Long memoId);
    PaginationDto<List<GetAllResponse>> getAllMemos(Pageable pageable);

    Memo validteMemoById(Long memoId);
    void validateCreatedUser(Memo memo);
}
