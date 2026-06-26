package com.elearning.controllers;

import com.elearning.models.dto.AuthRequest;
import com.elearning.models.dto.RegisterRequest;

import com.elearning.models.services.AuthService;

import lombok.RequiredArgsConstructor;


import com.elearning.advice.ApiResponse;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody AuthRequest authRequest) {
        Map<String, String> data = authService.login(authRequest);
        return ResponseEntity.ok(ApiResponse.success(data, "Login successful"));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, String>>> register(@RequestBody RegisterRequest registerRequest) {
        Map<String, String> data = authService.register(registerRequest);
        return ResponseEntity.ok(ApiResponse.success(data, "Registration successful"));
    }
}
