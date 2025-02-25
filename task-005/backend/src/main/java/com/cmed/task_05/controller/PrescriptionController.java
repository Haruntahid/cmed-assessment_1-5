package com.cmed.task_05.controller;

import com.cmed.task_05.model.dtos.PrescriptionDto;
import com.cmed.task_05.model.entity.Prescription;
import com.cmed.task_05.response.ApiResponse;
import com.cmed.task_05.response.ResponseData;
import com.cmed.task_05.response.ServerResponse;
import com.cmed.task_05.service.PrescriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.cmed.task_05.constraints.Api_V1.*;

@RestController
@RequestMapping(PRESCRIPTION_API)
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<ApiResponse> savePrescription(@Valid @RequestBody PrescriptionDto dto){
        service.savePrescription(dto);
        return ServerResponse.created("Prescription created successfully!");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePrescription(@PathVariable long id,@Valid @RequestBody PrescriptionDto dto) {
        service.updatePrescription(id, dto);
        return ServerResponse.ok("Prescription updated successfully!");
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getPrescription(@PathVariable long id){
        service.findById(id);
        return ServerResponse.withData("Prescription found successfully!",service.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<ResponseData> getPrescriptions(
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
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePrescription(@PathVariable long id) {
        service.deletePrescription(id);
        return ServerResponse.ok("Prescription deleted successfully!");
    }



}
