package com.nkdark.web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WebController {

    @PostMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @RequestParam String password) {
        Map<String, Object> model = new LinkedHashMap<>();
        model.put("url", "/index");
        HttpSession session = request.getSession(true);
        if (password.equals("123")) {
            if (null != session.getAttribute("Logged") && (boolean) session.getAttribute("Logged")) {
                return model;
            }
            session.setMaxInactiveInterval(864000000);
            session.setAttribute("Logged", true);
            Cookie cookie = new Cookie("Logged", "true");
            cookie.setMaxAge(864000000);
            response.addCookie(cookie);
            return model;
        } else {
            model.put("error", "");
            return model;
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("Logged", "true");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "再见";
    }

    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("redirect:index.html");
    }


}
