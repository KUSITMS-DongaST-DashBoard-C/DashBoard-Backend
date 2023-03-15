package com.example.dashboardback.file.exception;

import com.example.dashboardback.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class FileException  extends ApplicationException {
    protected FileException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
