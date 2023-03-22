package com.example.dashboardback.contents.live.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class LiveConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EChartResponseMessage{
        GET_EXPECTEDUPLOAD_SUCCESS("Live 업로드 예정 데이터 요청 성공"),
        GET_UPLOADED_SUCCESS("Live 업로드 완료 데이터 요청 성공"),
        GET_LIVEORDERBYAPPLICANTNUM_SUCCESS("신청 인원수 따른 live 데이터 요청 성공"),
        GET_LIVEORDERBYVIEWNUM_SUCCESS("조회수에 따른 live 데이터 요청 성공");
        private final String message;
    }

}
