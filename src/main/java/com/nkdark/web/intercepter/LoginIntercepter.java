package com.nkdark.web.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Cookie[] cookies = request.getCookies();
            if (null != cookies && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    if ("Logged".equals(cookie.getName()) && Boolean.parseBoolean(cookie.getValue())) {
                        return true;
                    }
                }
            }
            if (null != request.getSession().getAttribute("Logged")) {
                return true;
            }
            response.sendRedirect(request.getContextPath() + "login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
