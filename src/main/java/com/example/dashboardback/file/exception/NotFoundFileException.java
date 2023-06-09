package com.example.dashboardback.file.exception;

import static com.example.dashboardback.file.constant.FileConstants.FileExceptionList.NOT_FOUND_FILE;

public class NotFoundFileException extends FileException{
    public NotFoundFileException() {
        super(NOT_FOUND_FILE.getErrorCode(),
                NOT_FOUND_FILE.getHttpStatus(),
                NOT_FOUND_FILE.getMessage()
        );
    }
}
