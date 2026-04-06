package com.patient.PatientHealthMonitoring.service;

import com.patient.PatientHealthMonitoring.dto.AuthResponse;
import com.patient.PatientHealthMonitoring.dto.LoginRequest;
import com.patient.PatientHealthMonitoring.dto.RegisterRequest;
import com.patient.PatientHealthMonitoring.dto.RegisterResponse;
import com.patient.PatientHealthMonitoring.entity.Patient;
import com.patient.PatientHealthMonitoring.exception.CustomException;
import com.patient.PatientHealthMonitoring.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Authentication Service
 * Handles patient registration and login business logic
 */
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private PatientRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Register a new patient with email, name, and password
     * @param request RegisterRequest containing patient details
     * @return RegisterResponse with registered patient information
     * @throws CustomException if email already exists
     */
    public RegisterResponse register(RegisterRequest request) {
        logger.info("Registration attempt for email: {}", request.getEmail());

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            logger.warn("Registration failed: Email already exists - {}", request.getEmail());
            throw new CustomException("Email already exists");
        }

        Patient p = new Patient();
        p.setName(request.getName());
        p.setEmail(request.getEmail());
        p.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(p);
        logger.info("Patient registered successfully: {}", request.getEmail());

        return RegisterResponse.builder()
                .email(p.getEmail())
                .name(p.getName())
                .message("Patient registered successfully")
                .build();
    }

    /**
     * Authenticate patient and generate JWT token
     * @param req LoginRequest containing email and password
     * @return AuthResponse with JWT token and user details
     * @throws CustomException if user not found or credentials invalid
     */
    public AuthResponse login(LoginRequest req) {
        logger.info("Login attempt for email: {}", req.getEmail());

        Patient p = repository.findByEmail(req.getEmail())
                .orElseThrow(() -> {
                    logger.warn("Login failed: User not found - {}", req.getEmail());
                    return new CustomException("User not found");
                });

        if (!passwordEncoder.matches(req.getPassword(), p.getPassword())) {
            logger.warn("Login failed: Invalid credentials for - {}", req.getEmail());
            throw new CustomException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(p.getEmail());
        logger.info("Login successful for email: {}", req.getEmail());

        return AuthResponse.builder()
                .token(token)
                .email(p.getEmail())
                .name(p.getName())
                .type("Bearer")
                .expiresIn(86400000) // 24 hours in milliseconds
                .build();
    }
}
