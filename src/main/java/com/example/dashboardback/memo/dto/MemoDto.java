package com.example.dashboardback.memo.dto;

import com.example.dashboardback.comment.dto.CommentDto;
import com.example.dashboardback.comment.dto.CommentDto.GetResponse;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemoDto {


    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "메모 생성을 위한 요청 객체")
    public static class CreateRequest {

        @NotBlank(message = "메모 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "메모 내용을 입력해 주세요.")
        private String content;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "메모 생성을 위한 응답객체")
    public static class CreateResponse {
        private Long memoId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String userName;
        private String imageUrl;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "메모 수정을 위한 요청 객체")
    public static class UpdateRequest {

        @NotBlank(message="메모 id를 입력해주세요.")
        @ApiModelProperty(notes="메모 id를 입력해주세요.")
        private String memoId;

        @NotBlank(message = "메모 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "메모 내용을 입력해 주세요.")
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "메모 수정을 위한 응답 객체")
    public static class UpdateResponse {

        private Long memoId;
    }

    @Setter
    @Getter
    @ApiModel(description = "메모 전체 조회를 위한 응답객체")
    public static class GetAllResponse {
        private Long memoId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String userName;
        private String imageUrl;
        private String userEmail;
        private List<GetResponse> comments=new ArrayList<>();

        @QueryProjection
        public GetAllResponse(Long memoId, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String userName, String userEmail, String imageUrl){
            this.memoId=memoId;
            this.content=content;
            this.createdAt=createdAt;
            this.updatedAt=updatedAt;
            this.userName=userName;
            this.userEmail=userEmail;
            this.imageUrl=imageUrl;
        }
    }


}
