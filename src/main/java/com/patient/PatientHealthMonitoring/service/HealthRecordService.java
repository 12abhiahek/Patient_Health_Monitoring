package com.patient.PatientHealthMonitoring.service;

import com.patient.PatientHealthMonitoring.dto.HealthRecordRequest;
import com.patient.PatientHealthMonitoring.entity.HealthRecord;
import com.patient.PatientHealthMonitoring.entity.Patient;
import com.patient.PatientHealthMonitoring.exception.CustomException;
import com.patient.PatientHealthMonitoring.repository.HealthRecordRepository;
import com.patient.PatientHealthMonitoring.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthRecordService {

    @Autowired
    private HealthRecordRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    public HealthRecord addRecord(Long patientId, HealthRecordRequest req) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found"));

        HealthRecord record = new HealthRecord();
        record.setPatient(patient);
        record.setBloodPressure(req.getBloodPressure());
        record.setSugarLevel(req.getSugarLevel());
        record.setHeartRate(req.getHeartRate());
        record.setCreatedAt(LocalDateTime.now());

        // Set status based on health check
        String status = checkHealthStatus(record);
        record.setStatus(status);

        repo.save(record);

        return record;
    }

    private String checkHealthStatus(HealthRecord r) {
        List<String> issues = new ArrayList<>();

        // Check Blood Pressure: assume format "120/80", check systolic >140 or diastolic >90
        String bp = r.getBloodPressure();
        if (bp != null && bp.contains("/")) {
            String[] parts = bp.split("/");
            try {
                int systolic = Integer.parseInt(parts[0].trim());
                int diastolic = Integer.parseInt(parts[1].trim());
                if (systolic > 140) {
                    issues.add("High Systolic BP (" + systolic + ")");
                }
                if (diastolic > 90) {
                    issues.add("High Diastolic BP (" + diastolic + ")");
                }
            } catch (Exception e) {
                // ignore
            }
        }

        // Check Heart Rate
        if (r.getHeartRate() != null && r.getHeartRate() > 120) {
            issues.add("High Heart Rate (" + r.getHeartRate() + " bpm)");
        }

        // Check Sugar Level
        if (r.getSugarLevel() != null && r.getSugarLevel() > 200) {
            issues.add("High Sugar Level (" + r.getSugarLevel() + " mg/dL)");
        }

        if (issues.isEmpty()) {
            return "Normal";
        } else {
            return String.join(", ", issues);
        }
    }

    public Page<HealthRecord> getRecords(Long patientId, int page, int size) {
        return repo.findByPatientId(patientId, PageRequest.of(page, size));
    }
}
