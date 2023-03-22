package com.example.dashboardback.contents.original.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class OriginalConstants {
    @Getter
    @RequiredArgsConstructor
    public enum EOriginalResponseMessage{
        GETDETAIL_SUCCESS("오리지널 세부 콘텐츠 조회를 성공했습니다"),
        GETUPLOAD_SUCCESS("오리지널 업로드 예정 콘텐츠 조회를 성공했습니다");
        private final String message;
    }


}
