package com.example.dashboardback.memo.exception;

import com.example.dashboardback.memo.constant.MemoConstants;

import static com.example.dashboardback.memo.constant.MemoConstants.BoardExceptionList.NOT_FOUND_MEMO;

public class NotFoundMemoException extends MemoException{
    public NotFoundMemoException(){
        super(NOT_FOUND_MEMO.getErrorCode(),NOT_FOUND_MEMO.getHttpStatus(), NOT_FOUND_MEMO.getMessage());
    }
}
