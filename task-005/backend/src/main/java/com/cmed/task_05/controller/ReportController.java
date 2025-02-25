package com.cmed.task_05.controller;

import com.cmed.task_05.response.ResponseData;
import com.cmed.task_05.response.ServerResponse;
import com.cmed.task_05.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cmed.task_05.constraints.Api_V1.*;

@RestController
@RequestMapping(REPORT_API)
@RequiredArgsConstructor
public class ReportController {

    private final PrescriptionService service;

    @GetMapping
    public ResponseEntity<ResponseData> getReport() {
        service.dayWiseCount();
        return ServerResponse.withData("Successfully fetch report", service.dayWiseCount());
    }
}
