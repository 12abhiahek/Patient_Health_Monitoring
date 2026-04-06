package com.patient.PatientHealthMonitoring.controller;

import com.patient.PatientHealthMonitoring.dto.ApiResponse;
import com.patient.PatientHealthMonitoring.dto.AuthResponse;
import com.patient.PatientHealthMonitoring.dto.LoginRequest;
import com.patient.PatientHealthMonitoring.dto.RegisterRequest;
import com.patient.PatientHealthMonitoring.dto.RegisterResponse;
import com.patient.PatientHealthMonitoring.exception.CustomException;
import com.patient.PatientHealthMonitoring.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller
 * Handles patient registration and login operations
 * Provides JWT token-based authentication with proper response structures
 *
 * @author Patient Health Monitoring System
 * @version 1.0
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Patient registration and login endpoints")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService service;

    /**
     * Register a new patient account
     *
     * @param req RegisterRequest containing name, email, and password
     * @param httpRequest HttpServletRequest for capturing request details
     * @return ResponseEntity with ApiResponse containing registration details and 201 Created status
     *
     * Status Codes:
     * - 201 CREATED: Patient registered successfully
     * - 400 BAD_REQUEST: Email already exists or validation fails
     * - 500 INTERNAL_SERVER_ERROR: Unexpected server error
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new patient",
               description = "Creates a new patient account with email, name, and password. Returns 201 Created on success.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Patient registered successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or email already exists"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest req,
            HttpServletRequest httpRequest) {
        try {
            logger.info("Registration request received for email: {}", req.getEmail());
            RegisterResponse response = service.register(req);

            ApiResponse<RegisterResponse> apiResponse = ApiResponse.success(
                    HttpStatus.CREATED.value(),
                    "Patient registered successfully. You can now login with your credentials.",
                    response
            );
            apiResponse.setPath(httpRequest.getRequestURI());

            logger.info("Registration successful for email: {}", req.getEmail());
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (CustomException e) {
            logger.error("Registration validation error: {}", e.getMessage());
            ApiResponse<RegisterResponse> apiResponse = ApiResponse.error(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage()
            );
            apiResponse.setPath(httpRequest.getRequestURI());
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Unexpected error during registration", e);
            ApiResponse<RegisterResponse> apiResponse = ApiResponse.error(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An unexpected error occurred during registration. Please try again later."
            );
            apiResponse.setPath(httpRequest.getRequestURI());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Authenticate patient and generate JWT token
     *
     * @param req LoginRequest containing email and password
     * @param httpRequest HttpServletRequest for capturing request details
     * @return ResponseEntity with ApiResponse containing JWT token and user details with 200 OK status
     *
     * Status Codes:
     * - 200 OK: Login successful, JWT token returned
     * - 400 BAD_REQUEST: Invalid email or password
     * - 404 NOT_FOUND: User not found
     * - 500 INTERNAL_SERVER_ERROR: Unexpected server error
     */
    @PostMapping("/login")
    @Operation(summary = "Patient login",
               description = "Authenticates patient and returns JWT token (Bearer token) valid for 24 hours. Returns 200 OK on success.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Login successful, JWT token returned",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid email or password"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest req,
            HttpServletRequest httpRequest) {
        try {
            logger.info("Login request received for email: {}", req.getEmail());
            AuthResponse response = service.login(req);

            ApiResponse<AuthResponse> apiResponse = ApiResponse.success(
                    "Login successful. Use the returned token in the Authorization header as 'Bearer <token>' for subsequent requests.",
                    response
            );
            apiResponse.setPath(httpRequest.getRequestURI());

            logger.info("Login successful for email: {}", req.getEmail());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (CustomException e) {
            logger.warn("Login failed for email: {} - {}", req.getEmail(), e.getMessage());

            // Determine appropriate status code based on error message
            HttpStatus status = e.getMessage().contains("not found") ?
                    HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            int statusCode = status == HttpStatus.NOT_FOUND ? 404 : 400;

            ApiResponse<AuthResponse> apiResponse = ApiResponse.error(statusCode, e.getMessage());
            apiResponse.setPath(httpRequest.getRequestURI());
            return new ResponseEntity<>(apiResponse, status);
        } catch (Exception e) {
            logger.error("Unexpected error during login", e);
            ApiResponse<AuthResponse> apiResponse = ApiResponse.error(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "An unexpected error occurred during login. Please try again later."
            );
            apiResponse.setPath(httpRequest.getRequestURI());
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
