package com.example.group1bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(@CookieValue(name = "user_session", required = false) String userSession) {
        if (userSession == null) {
            return "redirect:/";
        }
        return "forward:/dashboard.html";
    }
}
