package com.example.dashboardback.comment.repository;

import com.example.dashboardback.comment.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryCustom {
    Optional<Comment> findNotDeletedByCommentId(Long commentId);
    List<Comment> findAllCommentsByMemoId(Long memoId);
}
