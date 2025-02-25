package com.cmed.task_05.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse extends ApiResponse {
    private final List<String> errors;

    public ErrorResponse(Integer code, String status, String message, List<String> errors) {
        super(code, status, message);
        this.errors = errors;
    }
}
