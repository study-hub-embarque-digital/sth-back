package com.studyhub.sth.libs.controller.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {
    @Override
    public  boolean  preHandle (HttpServletRequest request, HttpServletResponse response, Object handler)  throws Exception {
        log.info("{} {} {}", request.getMethod(), request.getRequestURI(), request.getRequestId());
        return true;
    }
}
