package com.example.dashboardback.comment.exception;

import com.example.dashboardback.comment.constant.CommentConstants;

import static com.example.dashboardback.comment.constant.CommentConstants.CommentExceptionList.NOT_FOUND_COMMENT;

public class NotFoundCommentException extends CommentException {
    public NotFoundCommentException() {
        super(NOT_FOUND_COMMENT.getErrorCode(),
                NOT_FOUND_COMMENT.getHttpStatus(),
                NOT_FOUND_COMMENT.getMessage()
        );
    }
}
