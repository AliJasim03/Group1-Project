package com.example.group1bankingapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AuthController {

    private List<User> users;

    public AuthController() {
        loadUsers();
    }

    private void loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(
                    new ClassPathResource("users.json").getInputStream(),
                    new TypeReference<List<User>>() {
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
            users = List.of();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        boolean isValidUser = checkCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if (isValidUser) {
            Cookie cookie = new Cookie("user_session", loginRequest.getEmail());
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }

        Map<String, Boolean> responseBody = new HashMap<>();
        responseBody.put("success", isValidUser);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("user_session", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    private boolean checkCredentials(String email, String password) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }
}


class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
