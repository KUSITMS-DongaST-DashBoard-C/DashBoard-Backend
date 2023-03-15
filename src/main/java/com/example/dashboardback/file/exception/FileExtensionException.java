package com.example.dashboardback.file.exception;

import static com.example.dashboardback.file.constant.FileConstants.FileExceptionList.FILE_EXTENSION;

public class FileExtensionException extends FileException {
    public FileExtensionException() {
        super(FILE_EXTENSION.getErrorCode(),
                FILE_EXTENSION.getHttpStatus(),
                FILE_EXTENSION.getMessage()
        );
    }
}
