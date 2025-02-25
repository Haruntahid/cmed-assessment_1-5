package com.health.cmed_task_01.controller;

import com.health.cmed_task_01.entity.dtos.PrescriptionDto;
import com.health.cmed_task_01.entity.model.Prescription;
import com.health.cmed_task_01.response.ApiResponse;
import com.health.cmed_task_01.response.ResponseWithData;
import com.health.cmed_task_01.response.ServerResponse;
import com.health.cmed_task_01.services.PrescriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/prescriptions")
    public ResponseEntity<ApiResponse> addPrescription(@Valid @RequestBody PrescriptionDto dto) {
        service.addPrescription(dto);
        return ServerResponse.ok("Successfully added prescription");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/prescriptions")
    public ResponseEntity<ResponseWithData> getPrescriptions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Prescription> pageDate = service.getAllPrescriptions(startDate,endDate,pageable);

        return ServerResponse.withPaginatedData("Successfully fetch prescriptions", pageDate);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/prescriptions/{id}")
    public ResponseEntity<ResponseWithData> getPrescription(@PathVariable Long id) {
        service.findById(id);
        return ServerResponse.withData("Successfully find Prescription", service.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/prescriptions/{id}")
    public ResponseEntity<ApiResponse> updatePrescription(@PathVariable Long id, @Valid @RequestBody PrescriptionDto dto) {
        service.updatePrescription(id, dto);
        return ServerResponse.ok("Successfully updated prescription");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/prescriptions/{id}")
    public ResponseEntity<ApiResponse> deletePrescription(@PathVariable Long id) {
        service.deletePrescription(id);
        return ServerResponse.ok("Successfully deleted prescription");
    }


    @GetMapping("/posts")
    public ResponseEntity<ResponseWithData> getPosts() {
        service.getDataFromExternalApi();
        return ServerResponse.withData("Successfully get data from external api", service.getDataFromExternalApi());
    }

}
