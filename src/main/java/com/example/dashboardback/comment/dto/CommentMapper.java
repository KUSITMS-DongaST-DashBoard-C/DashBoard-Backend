package com.example.dashboardback.comment.dto;

import com.example.dashboardback.comment.dto.CommentDto.CreateRequest;
import com.example.dashboardback.comment.dto.CommentDto.CreateResponse;
import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.global.config.security.utils.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CommentMapperSupport.class, imports = SecurityUtils.class)
public interface CommentMapper {

    @Mapping(target = "content", source = "content")
    @Mapping(target = "memo", source = "memoId", qualifiedByName = "getMemo")
    @Mapping(target = "admin", expression = "java(SecurityUtils.getLoggedInUser())")
    Comment toEntity(CreateRequest createRequest);

    @Mapping(target = "commentId", source = "commentId")
    CreateResponse toCreateResponse(Comment comment);

}
