package com.patient.PatientHealthMonitoring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Generic API Response wrapper for all endpoints
 * Provides consistent response structure for success and error scenarios
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int code;
    private String message;
    private String status;
    private T data;
    private LocalDateTime timestamp;
    private String path;

    /**
     * Create a successful response
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .message(message)
                .status("SUCCESS")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create a success response with custom status code
     */
    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .status("SUCCESS")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create an error response
     */
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .status("ERROR")
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Create a failure response
     */
    public static <T> ApiResponse<T> failure(int code, String message, String status) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

