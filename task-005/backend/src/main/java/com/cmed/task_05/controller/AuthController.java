package com.cmed.task_05.controller;

import com.cmed.task_05.model.dtos.LoginDto;
import com.cmed.task_05.model.dtos.UserDto;
import com.cmed.task_05.response.ApiResponse;
import com.cmed.task_05.response.LoginResponse;
import com.cmed.task_05.response.ServerResponse;
import com.cmed.task_05.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cmed.task_05.constraints.Api_V1.*;

@RestController
@RequestMapping(API_V1_AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(SIGN_UP)
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody UserDto dto) {
        authService.register(dto);
        return ServerResponse.created("Register Successfully!");
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDto dto) {
        authService.verify(dto);
        return ServerResponse.successLoginResponse("Login Successfully", authService.verify(dto));
    }

}
