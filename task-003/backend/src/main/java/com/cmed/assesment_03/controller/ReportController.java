package com.cmed.assesment_03.controller;

import com.cmed.assesment_03.response.ResponseWithData;
import com.cmed.assesment_03.response.ServerResponse;
import com.cmed.assesment_03.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cmed.assesment_03.constants.API.*;

@RestController
@RequestMapping(BASE_API)
@RequiredArgsConstructor
public class ReportController {

    private final PrescriptionService service;

    @GetMapping(REPORT_API)
    public ResponseEntity<ResponseWithData> getReport() {
        service.dayWiseCount();
        return ServerResponse.withData("Report fetched successfully", service.dayWiseCount());
    }

    @Operation(summary = "get data from external api")
    @GetMapping(EXTERNAL_API)
    public ResponseEntity<ResponseWithData> getPosts() {
        service.getDataFromExternalApi();
        return ServerResponse.withData("Data fetched successfully", service.getDataFromExternalApi());
    }

}
