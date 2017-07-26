package com.shop.Utils;

import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {
    public static String getUserName(HttpServletRequest request){
        SecurityContextImpl securityContext = (SecurityContextImpl) request
                .getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext != null){
            return securityContext.getAuthentication().getName();
        }
        else {
            return null;
        }
    }
}
