package com.example.group1bankingapp.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Allow access to login page and login API
        if (request.getRequestURI().equals("/") || request.getRequestURI().startsWith("/api/login")) {
            return true;
        }

        // Check for user_session cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user_session") && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                    return true;
                }
            }
        }

        // If no valid session found, redirect to login page
        response.sendRedirect("/");
        return false;
    }
}
