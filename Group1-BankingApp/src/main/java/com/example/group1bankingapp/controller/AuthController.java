package com.example.group1bankingapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<Map<String, Boolean>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Received login request for email: " + loginRequest.getEmail());
        boolean isValidUser = checkCredentials(loginRequest.getEmail(), loginRequest.getPassword());
        System.out.println("Is valid user: " + isValidUser);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", isValidUser);

        return ResponseEntity.ok(response);
    }

    private boolean checkCredentials(String email, String password) {
        System.out.println("Checking credentials for email: " + email);
        System.out.println("Number of users in list: " + users.size());
        return users.stream()
                .anyMatch(user -> {
                    boolean match = user.getEmail().equals(email) && user.getPassword().equals(password);
                    System.out.println("Checking user: " + user.getEmail() + ", match: " + match);
                    return match;
                });
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
