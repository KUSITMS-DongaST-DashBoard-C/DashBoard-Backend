package com.example.dashboardback.memo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class MemoConstants {
    @Getter
    @RequiredArgsConstructor
    public enum EMemoController{
        LOCATION_ID_PATH("/{id}"),
        GET_METHOD("get"),
        DELETE_METHOD("delete"),
        UPDATE_METHOD("update");
        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum EBoardResponseMessage{
        CREATE_MEMO_SUCCESS("메모를 생성했습니다."),
        UPDATE_MEMO_SUCCESS("메모를 수정했습니다."),
        DELETE_MEMO_SUCCESS("메모를 삭제했습니다."),
        GET_ALL_DETAIL_MEMO_SUCCESS("메모를 작성 시간순으로 조회했습니다.");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum BoardExceptionList {
        NOT_FOUND_MEMO("M0001", HttpStatus.NOT_FOUND, "해당 memoId로 Memo를 찾을 수 없습니다."),
        NOT_MEMO_WRITER("M0002", HttpStatus.CONFLICT, "해당 메모를 작성한 관리자가 아닙니다.");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
