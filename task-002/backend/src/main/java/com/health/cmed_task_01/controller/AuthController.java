package com.health.cmed_task_01.controller;

import com.health.cmed_task_01.entity.dtos.LoginDto;
import com.health.cmed_task_01.entity.dtos.UsersDto;
import com.health.cmed_task_01.response.ApiResponse;
import com.health.cmed_task_01.response.LoginResponse;
import com.health.cmed_task_01.response.ServerResponse;
import com.health.cmed_task_01.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UsersDto dto) {
        authService.register(dto);
        return ServerResponse.create("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDto dto) {
        authService.verify(dto);
        return ServerResponse.login("Login Successfully",authService.verify(dto));
    }

}
