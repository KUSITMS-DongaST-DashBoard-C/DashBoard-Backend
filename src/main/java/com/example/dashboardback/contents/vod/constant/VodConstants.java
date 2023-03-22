package com.example.dashboardback.contents.vod.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class VodConstants {
    @Getter
    @RequiredArgsConstructor
    public enum EVodResponseMessage{
        GETDETAIL_SUCCESS("VOD 세부 콘텐츠 조회를 성공했습니다"),
        GETUPLOAD_SUCCESS("VOD 업로드 예정 콘텐츠 조회를 성공했습니다");
        private final String message;
    }
}
