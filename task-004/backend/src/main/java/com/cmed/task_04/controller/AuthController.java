package com.cmed.task_04.controller;



import com.cmed.task_04.entity.dtos.LoginDto;
import com.cmed.task_04.entity.dtos.UsersDto;
import com.cmed.task_04.response.ApiResponse;
import com.cmed.task_04.response.LoginResponse;
import com.cmed.task_04.response.ServerResponse;
import com.cmed.task_04.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cmed.task_04.constants.API.*;


@RestController
@RequestMapping(BASE_API)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService services;


    @PostMapping(REGISTER_API)
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UsersDto dto) {
        services.registerUser(dto);
        return ServerResponse.create("User registered successfully!");
    }

    @PostMapping(LOGIN_API)
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginDto dto) {
        services.verify(dto);
        return ServerResponse.login("Login Successfully!", services.verify(dto));
    }

}
