package com.example.dashboardback.contents.original.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class OriginalDto {

    @Getter
    @ApiModel(description = "업로드 콘텐츠 응답 객체")
    public static class UploadInfoResponse{
        private String thumbnailUrl;
        private String title;
        private String major;
        private LocalDate expectedUploadTime;


        @QueryProjection
        public UploadInfoResponse(String thumbnailUrl, String title, String major, LocalDateTime expectedUploadTime){
            this.thumbnailUrl=thumbnailUrl;
            this.title=title;
            this.major=major;
            this.expectedUploadTime=expectedUploadTime.toLocalDate();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "세부 콘텐츠 분석 요청 객체")
    public static class DetailInfoRequest{
        @NotBlank(message = "기한 시작 일을 입력해 주세요.")
        @ApiModelProperty(notes = "기한 시작 일을 입력해 주세요.")
        @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}"
                , message = "날짜 형식이 맞지 않습니다. yyyy-mm-dd 형식으로 입력해주세요.")
        private String startDate;

        @NotBlank(message = "기한 종료 일을 입력해 주세요.")
        @ApiModelProperty(notes = "기한 종료 일을 입력해 주세요.")
        @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}"
                , message = "날짜 형식이 맞지 않습니다. yyyy-mm-dd 형식으로 입력해주세요.")
        private String endDate;
    }

    @Getter
    @Builder
    @ApiModel(description = "세부 콘텐츠 분석 응답 객체")
    public static class DetailInfoResponse{
        private String thumbnailUrl;
        private String seriesName;
        private Integer episodeNum;
        private LocalDateTime uploadDate;
        private Integer reviewNum;
        private Integer likeNum;
        private Integer commentNum;
        private Long viewNum;
        private String major;

        @QueryProjection
        public DetailInfoResponse(String thumbnailUrl, String seriesName, Integer episodeNum, LocalDateTime uploadDate, Integer reviewNum, Integer likeNum, Integer commentNum, Long viewNum, String major){
            this.thumbnailUrl=thumbnailUrl;
            this.seriesName=seriesName;
            this.episodeNum=episodeNum;
            this.uploadDate=uploadDate;
            this.reviewNum=reviewNum;
            this.likeNum=likeNum;
            this.commentNum=commentNum;
            this.viewNum=viewNum;
            this.major=major;
        }
    }


}
