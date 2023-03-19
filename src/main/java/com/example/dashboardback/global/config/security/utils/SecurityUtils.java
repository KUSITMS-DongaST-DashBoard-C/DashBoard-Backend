package com.example.dashboardback.global.config.security.utils;

import com.example.dashboardback.global.config.security.exception.RequiredLoggedInException;
import com.example.dashboardback.global.config.security.jwt.UserDetailsImpl;
import com.example.dashboardback.admin.entity.Admin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

    public static Admin getLoggedInUser() {
        try {
            return ((UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).getAdmin();
        } catch (NullPointerException e) {
            throw new RequiredLoggedInException();
        }
    }

}
