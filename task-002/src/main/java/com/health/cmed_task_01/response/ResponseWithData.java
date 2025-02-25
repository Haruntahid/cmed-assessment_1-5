package com.health.cmed_task_01.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWithData extends ApiResponse{

    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
    private final Object data;


    public ResponseWithData(Integer statusCode, String status, String message, Object data) {
        super(statusCode, status, message);
        this.data = data;
    }

    public ResponseWithData(Integer statusCode, String status, String message,
                            Integer page, Integer size, Integer totalPages, Long totalElements, Object data) {
        super(statusCode, status, message);
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = data;
    }
}
