package com.example.group1bankingapp.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            boolean loggedIn = Arrays.stream(cookies)
                    .anyMatch(cookie -> "user_session".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty());
            if (loggedIn) {
                return true;
            }
        }
        response.sendRedirect("/");
        return false;
    }
}
