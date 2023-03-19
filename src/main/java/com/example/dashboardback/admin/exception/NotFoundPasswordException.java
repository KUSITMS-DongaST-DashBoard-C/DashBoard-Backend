package com.example.dashboardback.admin.exception;

import com.example.dashboardback.admin.constant.AdminConstants;

public class NotFoundPasswordException extends AdminException {
    public NotFoundPasswordException() {
        super(AdminConstants.UserExceptionList.NOT_FOUND_PASSWORD.getErrorCode(),
                AdminConstants.UserExceptionList.NOT_FOUND_PASSWORD.getHttpStatus(),
                AdminConstants.UserExceptionList.NOT_FOUND_PASSWORD.getMessage());
    }
}

