package com.patient.PatientHealthMonitoring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class  HealthRecordRequest {

    @NotBlank(message = "Blood pressure is required")
    private String bloodPressure;

    @NotNull(message = "Sugar level is required")
    @Min(value = 0, message = "Sugar level must be positive")
    private Double sugarLevel;

    @NotNull(message = "Heart rate is required")
    @Min(value = 0, message = "Heart rate must be positive")
    private Integer heartRate;
}
