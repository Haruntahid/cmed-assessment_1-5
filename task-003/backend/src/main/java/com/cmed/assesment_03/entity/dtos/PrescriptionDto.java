package com.cmed.assesment_03.entity.dtos;

import com.cmed.assesment_03.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionDto {
    @NotNull(message = "Prescription date is mandatory")
    @PastOrPresent(message = "Prescription date cannot be in the future")
    private LocalDate prescriptionDate;

    @NotNull(message = "Patient name is mandatory")
    @Size(min = 3,max = 40, message="Patient Name must be between 3 to 40 characters")
    private String name;

    @NotNull(message = "Patient age is mandatory")
    @Min(value = 0, message = "Patient age must be at least 0")
    @Max(value = 150, message = "Patient age must be less than or equal to 150")
    private int age;

    @NotNull(message = "Patient gender is mandatory")
    private Gender gender;

    private String diagnosis;

    private String medicines;

    @Future(message = "Next visit date must be a future date")
    private LocalDate nextVisitDate;
}
