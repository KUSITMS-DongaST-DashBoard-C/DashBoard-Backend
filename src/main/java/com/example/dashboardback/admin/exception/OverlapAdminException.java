package com.example.dashboardback.admin.exception;

import com.example.dashboardback.admin.constant.AdminConstants;

public class OverlapAdminException extends AdminException {

    public OverlapAdminException() {
        super(AdminConstants.UserExceptionList.OVERLAP_USER.getErrorCode(),
                AdminConstants.UserExceptionList.OVERLAP_USER.getHttpStatus(),
                AdminConstants.UserExceptionList.OVERLAP_USER.getMessage());
    }
}
