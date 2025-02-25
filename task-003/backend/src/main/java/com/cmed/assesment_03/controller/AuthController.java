package com.cmed.assesment_03.controller;


import com.cmed.assesment_03.entity.dtos.LoginDto;
import com.cmed.assesment_03.entity.dtos.UsersDto;
import com.cmed.assesment_03.response.ApiResponse;
import com.cmed.assesment_03.response.LoginResponse;
import com.cmed.assesment_03.response.ServerResponse;
import com.cmed.assesment_03.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cmed.assesment_03.constants.API.*;

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
