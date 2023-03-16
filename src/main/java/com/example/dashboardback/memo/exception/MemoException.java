package com.example.dashboardback.memo.exception;

import com.example.dashboardback.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class MemoException extends ApplicationException {
    protected MemoException(String errorCode, HttpStatus httpStatus, String message){
        super(errorCode, httpStatus, message);
    }
}
