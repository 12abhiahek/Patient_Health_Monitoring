package com.patient.PatientHealthMonitoring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
}
