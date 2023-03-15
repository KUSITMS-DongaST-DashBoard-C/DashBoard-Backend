package com.example.dashboardback.user.exception;

import com.example.dashboardback.user.constant.UserConstants;

public class OverlapUserException extends UserException {

    public OverlapUserException() {
        super(UserConstants.UserExceptionList.OVERLAP_USER.getErrorCode(),
                UserConstants.UserExceptionList.OVERLAP_USER.getHttpStatus(),
                UserConstants.UserExceptionList.OVERLAP_USER.getMessage());
    }
}
