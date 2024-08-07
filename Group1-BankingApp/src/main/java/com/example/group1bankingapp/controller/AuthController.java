package com.example.group1bankingapp.controller;

import com.example.group1bankingapp.repository.CustomerRepository;
import com.example.group1bankingapp.model.Customer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

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
        Optional<Customer> customer = customerRepository.findByEmailAndPassword(email, password);
        return customer.isPresent();
    }
}

class LoginRequest {
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
