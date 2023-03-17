package com.example.dashboardback.memo.exception;

import static com.example.dashboardback.memo.constant.MemoConstants.BoardExceptionList.NOT_MEMO_WRITER;

public class NotMemoWriterException extends MemoException{
    public NotMemoWriterException(){
        super(NOT_MEMO_WRITER.getErrorCode(),NOT_MEMO_WRITER.getHttpStatus(), NOT_MEMO_WRITER.getMessage());
    }
}
