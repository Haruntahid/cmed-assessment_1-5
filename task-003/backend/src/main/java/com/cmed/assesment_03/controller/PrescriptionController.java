package com.cmed.assesment_03.controller;

import com.cmed.assesment_03.entity.dtos.PrescriptionDto;
import com.cmed.assesment_03.entity.model.Prescription;
import com.cmed.assesment_03.response.ApiResponse;
import com.cmed.assesment_03.response.ResponseWithData;
import com.cmed.assesment_03.response.ServerResponse;
import com.cmed.assesment_03.service.PrescriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.cmed.assesment_03.constants.API.*;

@RestController
@RequestMapping(PRESCRIPTION_API)
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<ApiResponse> savePrescription(@Valid @RequestBody PrescriptionDto dto){
        service.savePrescription(dto);
        return ServerResponse.create("Prescription created successfully!");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithData> getPrescription(@PathVariable long id){
        service.findById(id);
        return ServerResponse.withData("Prescription found successfully!",service.findById(id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
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
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePrescription(@PathVariable long id) {
        service.deletePrescription(id);
        return ServerResponse.ok("Prescription deleted successfully!");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePrescription(@PathVariable long id,@Valid @RequestBody PrescriptionDto dto) {
        service.updatePrescription(id, dto);
        return ServerResponse.ok("Prescription updated successfully!");
    }


}
