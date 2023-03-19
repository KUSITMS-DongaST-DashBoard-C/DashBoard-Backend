package com.example.dashboardback.memo.dto;

import com.example.dashboardback.global.config.security.utils.SecurityUtils;
import com.example.dashboardback.memo.dto.MemoDto.CreateRequest;
import com.example.dashboardback.memo.dto.MemoDto.CreateResponse;
import com.example.dashboardback.memo.dto.MemoDto.UpdateResponse;
import com.example.dashboardback.memo.entity.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = SecurityUtils.class)
public interface MemoMapper {

    @Mapping(target = "content", source = "createRequest.content")
    @Mapping(target = "admin", expression = "java(SecurityUtils.getLoggedInUser())")
    Memo toEntity(CreateRequest createRequest);

    @Mapping(target="content", source="content")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target="memoId", source="memoId")
    @Mapping(target="adminName", expression = "java(SecurityUtils.getLoggedInUser().getName())")
    CreateResponse toCreateResponse(Memo memo);

    @Mapping(target="memoId", source="memoId")
    UpdateResponse toUpdateResponse(Memo memo);

}
