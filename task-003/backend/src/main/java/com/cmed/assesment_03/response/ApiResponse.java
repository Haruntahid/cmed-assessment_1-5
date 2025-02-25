package com.cmed.assesment_03.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ApiResponse{
    private final Integer code;
    private final String status;
    private final String message;
}
