package com.health.cmed_task_01.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final Integer statusCode;
    private final String status;
    private final List<String> errors;
}
