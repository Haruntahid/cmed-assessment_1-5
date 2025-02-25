package com.health.cmed_task_01.controller;

import com.health.cmed_task_01.response.ResponseWithData;
import com.health.cmed_task_01.response.ServerResponse;
import com.health.cmed_task_01.services.PrescriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ReportController {

    private final PrescriptionService service;

    @GetMapping("/report")
    public ResponseEntity<ResponseWithData> getDayWiseCount() {
        service.dayWiseCount();
        return ServerResponse.withData("Successfully get day wise count", service.dayWiseCount());
    }

}
