package com.cmed.assesment_03.response;

import lombok.Getter;

@Getter
public class LoginResponse extends ApiResponse {
    private final String token;

    public LoginResponse(Integer code, String status, String message, String token) {
        super(code, status, message);
        this.token = token;
    }

}
