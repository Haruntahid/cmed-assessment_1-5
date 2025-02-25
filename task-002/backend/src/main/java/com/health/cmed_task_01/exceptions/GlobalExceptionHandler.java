package com.health.cmed_task_01.exceptions;

import com.health.cmed_task_01.response.ApiResponse;
import com.health.cmed_task_01.response.ErrorResponse;
import com.health.cmed_task_01.response.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ServerResponse.notFound(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse> handleUnauthorizedException(UnauthorizedException e) {
        return ServerResponse.unauthorized(e.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<ApiResponse> handleResourceAlreadyExist(ResourceAlreadyExist e) {
        return ServerResponse.conflict(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List <String> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        return ServerResponse.fieldResponse(errors);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiResponse> handleRestClientException(RestClientException e) {
        return ServerResponse.serviceUnavailable(e.getMessage());
    }
}
