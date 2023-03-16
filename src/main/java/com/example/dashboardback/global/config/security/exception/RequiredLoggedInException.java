package com.example.dashboardback.global.config.security.exception;

import com.example.dashboardback.global.config.security.constant.SecurityConstants;
import com.example.dashboardback.user.exception.UserException;

import static com.example.dashboardback.global.config.security.constant.SecurityConstants.SecurityExceptionList.REQUIRED_LOGGED_IN;

public class RequiredLoggedInException extends UserException {
    public RequiredLoggedInException() {
        super(REQUIRED_LOGGED_IN.getErrorCode(),
                REQUIRED_LOGGED_IN.getHttpStatus(),
                REQUIRED_LOGGED_IN.getMessage()
        );
    }
}
