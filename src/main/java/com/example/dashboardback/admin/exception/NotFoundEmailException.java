package com.example.dashboardback.admin.exception;

import com.example.dashboardback.admin.constant.AdminConstants;

public class NotFoundEmailException extends AdminException {
    public NotFoundEmailException() {
        super(AdminConstants.UserExceptionList.NOT_FOUND_EMAIL.getErrorCode(),
                AdminConstants.UserExceptionList.NOT_FOUND_EMAIL.getHttpStatus(),
                AdminConstants.UserExceptionList.NOT_FOUND_EMAIL.getMessage());
    }
}