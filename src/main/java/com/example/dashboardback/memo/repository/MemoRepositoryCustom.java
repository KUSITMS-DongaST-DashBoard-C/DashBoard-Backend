package com.example.dashboardback.memo.repository;

import com.example.dashboardback.memo.dto.MemoDto.GetAllResponse;
import com.example.dashboardback.memo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemoRepositoryCustom {
    Optional<Memo> findNotDeletedByMemoId(Long memoId);
    Page<GetAllResponse> findAllMemosByCreatedDate(Pageable pageable);
}
