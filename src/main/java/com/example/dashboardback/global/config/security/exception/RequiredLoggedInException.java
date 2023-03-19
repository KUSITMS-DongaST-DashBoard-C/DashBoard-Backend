package com.example.dashboardback.global.config.security.exception;

import com.example.dashboardback.admin.exception.AdminException;

import static com.example.dashboardback.global.config.security.constant.SecurityConstants.SecurityExceptionList.REQUIRED_LOGGED_IN;

public class RequiredLoggedInException extends AdminException {
    public RequiredLoggedInException() {
        super(REQUIRED_LOGGED_IN.getErrorCode(),
                REQUIRED_LOGGED_IN.getHttpStatus(),
                REQUIRED_LOGGED_IN.getMessage()
        );
    }
}
