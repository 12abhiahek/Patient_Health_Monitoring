package com.patient.PatientHealthMonitoring.repository;

import com.patient.PatientHealthMonitoring.entity.HealthRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {

    Page<HealthRecord> findByPatientId(Long patientId, Pageable pageable);
}
