package com.example.dashboardback.comment.dto;

import com.example.dashboardback.memo.entity.Memo;
import com.example.dashboardback.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentMapperSupport {

    private final MemoService memoService;

    @Named("getMemo")
    public Memo getMemo(Long memoId){
        return this.memoService.validteMemoById(memoId);
    }

}
