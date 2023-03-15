package com.example.dashboardback.image.exception;

import com.example.dashboardback.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class ImageException extends ApplicationException {
    protected ImageException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
