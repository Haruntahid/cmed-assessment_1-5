package com.health.cmed_task_01.response;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ApiResponse {
    private final Integer statusCode;
    private final String status;
    private final String message;
}
