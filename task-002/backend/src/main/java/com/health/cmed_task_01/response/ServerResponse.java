package com.health.cmed_task_01.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ServerResponse {


    private static ResponseEntity<ApiResponse> generateResponse(String status, String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ApiResponse(httpStatus.value(), status, message != null ? message : "Unexpected Error Occurred"
                ),
                httpStatus
        );
    }

    public static ResponseEntity<LoginResponse> login(String message, String token) {
        return ResponseEntity.ok().body(
                new LoginResponse(
                        HttpStatus.OK.value(),
                        "Success",
                        message,
                        token
                )
        );
    }

    public static ResponseEntity<ResponseWithData> withData(String message, Object data) {
        return ResponseEntity.ok().body(
                new ResponseWithData(
                        HttpStatus.OK.value(),
                        "Success",
                        message,
                        data
                )
        );
    }

    public static <T> ResponseEntity<ResponseWithData> withPaginatedData(String message, Page<T> pageData) {
        return ResponseEntity.ok().body(
                new ResponseWithData(
                        HttpStatus.OK.value(),
                        "Success",
                        message,
                        pageData.getNumber(),      //  page
                        pageData.getSize(),        //  size
                        pageData.getTotalPages(),  //  pages
                        pageData.getTotalElements(), //  elements
                        pageData.getContent() //data
                )
        );
    }


    private static ResponseEntity<ApiResponse> getSuccessResponse(String message, HttpStatus httpStatus) {
        return generateResponse("Success", message, httpStatus);
    }

    private static ResponseEntity<ApiResponse> getErrorResponse(String message, HttpStatus httpStatus) {
        return generateResponse("Failed", message, httpStatus);
    }


    public static ResponseEntity<ApiResponse> ok(String message) {
        return getSuccessResponse(message, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> create(String message) {
        return getSuccessResponse(message, HttpStatus.CREATED);
    }


    public static ResponseEntity<ApiResponse> notFound(String message) {
        return getErrorResponse(message, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<ApiResponse> conflict(String message) {
        return getErrorResponse(message, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<ApiResponse> unauthorized(String message) {
        return getErrorResponse(message, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<ApiResponse> serviceUnavailable(String message) {
        return getErrorResponse(message, HttpStatus.SERVICE_UNAVAILABLE);
    }



    public static ResponseEntity<ErrorResponse> fieldResponse(List<String> errors) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Failed",
                errors
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }


}
