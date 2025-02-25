package com.health.cmed_task_01.response;


import lombok.Getter;

@Getter
public class LoginResponse extends ApiResponse {
    private final String token;

    public LoginResponse(Integer statusCode, String status, String message, String token) {
        super(statusCode, status, message);
        this.token = token;
    }
}
