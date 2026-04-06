package com.patient.PatientHealthMonitoring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="health_records")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodPressure;
    private Double sugarLevel;
    private Integer heartRate;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime createdAt;

    private String status; // NEW FIELD: stores the health status (Normal, High Blood Pressure, High Sugar Level, High Heart Rate, etc.)
}
