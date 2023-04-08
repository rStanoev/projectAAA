package com.example.projecta.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component
public class UpdateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        var requestURI = request.getRequestURI();
        if (!requestURI.equals("/update")) {
            LocalTime now = LocalTime.now();
            if (now.getHour() == 22 && now.getMinute() >= 10 && now.getMinute() <= 12) {
                response.sendRedirect("/update");
                return false;
            }
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
