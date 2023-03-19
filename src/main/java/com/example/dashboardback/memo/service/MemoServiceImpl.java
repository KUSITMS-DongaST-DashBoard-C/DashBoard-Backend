package com.example.dashboardback.memo.service;

import com.example.dashboardback.global.config.security.utils.SecurityUtils;
import com.example.dashboardback.image.repository.ImageRepository;
import com.example.dashboardback.memo.dto.MemoDto;
import com.example.dashboardback.memo.dto.MemoDto.CreateResponse;
import com.example.dashboardback.memo.dto.MemoDto.GetAllResponse;
import com.example.dashboardback.memo.dto.MemoMapper;
import com.example.dashboardback.memo.entity.Memo;
import com.example.dashboardback.memo.exception.NotFoundMemoException;
import com.example.dashboardback.memo.exception.NotMemoWriterException;
import com.example.dashboardback.memo.repository.MemoRepository;
import com.example.dashboardback.admin.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoServiceImpl implements MemoService{

    private final MemoRepository memoRepository;
    private final MemoMapper memoMapper;
    private final ImageRepository imageRepository;

    @Override
    public CreateResponse createMemo(MemoDto.CreateRequest createRequest) {
        Memo memo=this.memoMapper.toEntity(createRequest);
        this.memoRepository.save(memo);


        CreateResponse createResponse=this.memoMapper.toCreateResponse(memo);
        createResponse.setImageUrl(imageRepository.findByUserId(SecurityUtils.getLoggedInUser().getAdminId()));
        return createResponse;
    }

    @Override
    public MemoDto.UpdateResponse updateMemo(MemoDto.UpdateRequest updateRequest) {
        Memo memo=this.validteMemoById(Long.parseLong(updateRequest.getMemoId()));
        this.validateCreatedUser(memo);
        memo.setContent(updateRequest.getContent());
        return this.memoMapper.toUpdateResponse(memo);
    }

    @Override
    public Memo deleteMemo(Long memoId) {
        Memo memo=this.validteMemoById(memoId);
        memo.setDeleted(true);
        return memo;
    }

    @Override
    public PaginationDto<List<GetAllResponse>> getAllMemos(Pageable pageable) {
        Page<GetAllResponse> page=this.memoRepository.findAllMemosByCreatedDate(pageable);
        List<GetAllResponse> data=page.get().collect(Collectors.toList());
        return PaginationDto.of(page,data);
    }

    /**
     * validate
     */

    public Memo validteMemoById(Long memoId){
        return this.memoRepository.findNotDeletedByMemoId(memoId).orElseThrow(NotFoundMemoException::new);
    }

    public void validateCreatedUser(Memo memo){
        if(!(memo.getAdmin().getAdminId().equals(SecurityUtils.getLoggedInUser().getAdminId()))) throw new NotMemoWriterException();
    }
}
