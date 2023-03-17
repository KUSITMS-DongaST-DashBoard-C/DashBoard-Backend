package com.example.dashboardback.comment.service;

import com.example.dashboardback.comment.dto.CommentDto;
import com.example.dashboardback.comment.dto.CommentDto.CreateRequest;
import com.example.dashboardback.comment.dto.CommentDto.CreateResponse;
import com.example.dashboardback.comment.dto.CommentDto.DeleteRequest;
import com.example.dashboardback.comment.dto.CommentDto.GetResponse;
import com.example.dashboardback.comment.entity.Comment;

import java.util.List;

public interface CommentService {
    CreateResponse createComment(CreateRequest createRequest);
    Comment deleteComment(DeleteRequest deleteRequest);
    List<GetResponse> getAllCommentsByMemoId(Long memoId);
    Comment validateCommentId(Long commentId);
}
