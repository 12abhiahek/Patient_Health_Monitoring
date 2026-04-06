package com.patient.PatientHealthMonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for patient registration
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private String email;
    private String name;
    private String message;
}

