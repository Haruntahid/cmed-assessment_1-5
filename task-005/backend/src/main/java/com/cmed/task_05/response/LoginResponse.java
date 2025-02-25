package com.cmed.task_05.response;

import lombok.Getter;

@Getter
public class LoginResponse extends ApiResponse {
    private final String token;

    public LoginResponse(int code, String status, String message, String token) {
        super(code, status, message);
        this.token = token;
    }
}
