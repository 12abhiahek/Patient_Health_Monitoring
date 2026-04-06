package com.patient.PatientHealthMonitoring;

import com.patient.PatientHealthMonitoring.dto.HealthRecordRequest;
import com.patient.PatientHealthMonitoring.entity.HealthRecord;
import com.patient.PatientHealthMonitoring.entity.Patient;
import com.patient.PatientHealthMonitoring.repository.HealthRecordRepository;
import com.patient.PatientHealthMonitoring.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
public class HealthRecordControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    private MockMvc mockMvc;

    private Patient testPatient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        // Create a test patient
        testPatient = new Patient();
        testPatient.setName("Test Patient");
        testPatient.setEmail("test@example.com");
        testPatient.setPassword("password");
        testPatient = patientRepository.save(testPatient);
    }

    @Test
    void testAddRecord() throws Exception {
        mockMvc.perform(post("/api/health-records/add/" + testPatient.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bloodPressure\":\"120/80\",\"sugarLevel\":90.0,\"heartRate\":70}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("Normal"));
    }

    @Test
    void testGetRecords() throws Exception {
        // Add a record first
        HealthRecord record = new HealthRecord();
        record.setPatient(testPatient);
        record.setBloodPressure("120/80");
        record.setSugarLevel(90.0);
        record.setHeartRate(70);
        record.setCreatedAt(LocalDateTime.now());
        record.setStatus("Normal");
        healthRecordRepository.save(record);

        mockMvc.perform(get("/api/health-records/" + testPatient.getId())
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
