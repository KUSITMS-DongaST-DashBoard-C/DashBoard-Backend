package com.example.dashboardback.comment.dto;

import com.example.dashboardback.comment.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public abstract class CommentDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "댓글 작성을 위한 요청 객체")
    public static class CreateRequest {

        @NotNull(message = "메모 id를 입력해 주세요.")
        @ApiModelProperty(notes = "메모 id를 입력해 주세요.")
        private Long memoId;

        @NotBlank(message = "댓글 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "댓글 내용을 입력해 주세요.")
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "댓글 작성을 위한 응답 객체")
    public static class CreateResponse {
        private Long commentId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "댓글 삭제를 위한 요청 객체")
    public static class DeleteRequest {
        @ApiModelProperty(notes = "댓글 id를 입력해주세요.")
        private Long commentId;
    }

    @Getter
    @Builder
    @ApiModel(description = "댓글 조회를 위한 응답 객체")
    public static class  GetResponse {
        private Long commentId;
        private String content;
        private Long userId;
        private String userName;
        private String userImageUrl;
        private LocalDateTime createdAt;

        public GetResponse(Long commentId, String content, Long userId, String userName, String userImageUrl, LocalDateTime createdAt) {
            this.commentId = commentId;
            this.content = content;
            this.userId = userId;
            this.userName = userName;
            this.userImageUrl=userImageUrl;
            this.createdAt = createdAt;
        }

        public static GetResponse convertCommentToDto(Comment comment) {

            return comment.isDeleted() == true ?
                    new GetResponse(comment.getCommentId(), "삭제된 댓글입니다.", null, null, null,null) :
                    new GetResponse(comment.getCommentId(), comment.getContent(), comment.getAdmin().getUserId(), comment.getAdmin().getName(), comment.getAdmin().getUserImage().getImageUrl(), comment.getCreatedAt());
        }
    }

}
