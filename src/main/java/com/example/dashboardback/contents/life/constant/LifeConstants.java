package com.example.dashboardback.contents.life.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class LifeConstants {

        @Getter
        @RequiredArgsConstructor
        public enum EChartResponseMessage{
                GET_EXPECTEDUPLOAD_SUCCESS("Life 업로드 예정 데이터 요청 성공"),
                GET_UPLOADED_SUCCESS("Life 업로드 완료 데이터 요청 성공"),
                GET_LIFEORDERBYVIEWNUM_SUCCESS("조회수에 따른 Life 데이터 요청 성공"),
                GET_LIFEORDERBYCOMMENTNUM_SUCCESS("댓글수에 따른 Life 데이터 요청 성공");
                private final String message;
        }
}
