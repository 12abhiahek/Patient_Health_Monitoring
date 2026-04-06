package com.patient.PatientHealthMonitoring.controller;

import com.patient.PatientHealthMonitoring.dto.HealthRecordRequest;
import com.patient.PatientHealthMonitoring.entity.HealthRecord;
import com.patient.PatientHealthMonitoring.service.HealthRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health-records")
public class HealthRecordController {

    @Autowired
    private HealthRecordService service;

    @PostMapping("/add/{patientId}")
    public ResponseEntity<HealthRecord> addRecord(@PathVariable Long patientId,
                            @Valid @RequestBody HealthRecordRequest req) {
        HealthRecord record = service.addRecord(patientId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(record);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Page<HealthRecord>> getRecords(
            @PathVariable Long patientId,
            @RequestParam int page,
            @RequestParam int size) {

        Page<HealthRecord> records = service.getRecords(patientId, page, size);
        return ResponseEntity.ok(records);
    }
}
