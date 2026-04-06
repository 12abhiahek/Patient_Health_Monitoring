package com.patient.PatientHealthMonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for authentication operations (login/register)
 * Contains authentication token and user information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private String email;
    private String name;
    private String type;
    private long expiresIn;

    public AuthResponse(String token, String email, String name) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.type = "Bearer";
        this.expiresIn = 86400000; // 24 hours in milliseconds
    }
}

