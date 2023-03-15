package com.example.dashboardback.image.exception;

import com.example.dashboardback.image.constant.ImageConstants;
import com.example.dashboardback.image.constant.ImageConstants.ImageExceptionList;

import static com.example.dashboardback.image.constant.ImageConstants.ImageExceptionList.*;

public class NotFoundImageException extends ImageException{
    public NotFoundImageException() {
        super(NOT_FOUND_IMAGE.getErrorCode(),
                NOT_FOUND_IMAGE.getHttpStatus(),
                NOT_FOUND_IMAGE.getMessage()
        );
    }
}
