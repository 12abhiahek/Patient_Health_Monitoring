# Testing the Health Record Status Feature with Postman

## Overview
The HealthRecord API now includes a `status` field that provides detailed information about any abnormalities detected in the health data.

## Steps to Test

### 1. Start the Application
```
java -jar target/PatientHealthMonitoring-0.0.1-SNAPSHOT.jar
```

### 2. Register a Patient
**Endpoint:** `POST http://localhost:8080/api/auth/register`

**Headers:**
```
Content-Type: application/json
```

**Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```
"User Registered Successfully"
```

---

### 3. Test Case 1: Normal Health Record
**Endpoint:** `POST http://localhost:8080/api/health-records/add/1`

**Headers:**
```
Content-Type: application/json
```

**Body (All values normal):**
```json
{
  "bloodPressure": "120/80",
  "sugarLevel": 95.0,
  "heartRate": 72
}
```

**Expected Response (201 Created):**
```json
{
  "id": 1,
  "bloodPressure": "120/80",
  "sugarLevel": 95.0,
  "heartRate": 72,
  "patient": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "password": "..."
  },
  "createdAt": "2026-04-06T21:56:00",
  "status": "Normal"
}
```

---

### 4. Test Case 2: High Blood Pressure
**Endpoint:** `POST http://localhost:8080/api/health-records/add/1`

**Body (High BP):**
```json
{
  "bloodPressure": "150/95",
  "sugarLevel": 95.0,
  "heartRate": 72
}
```

**Expected Response:**
```json
{
  "id": 2,
  "bloodPressure": "150/95",
  "sugarLevel": 95.0,
  "heartRate": 72,
  "patient": { ... },
  "createdAt": "2026-04-06T21:57:00",
  "status": "High Systolic BP (150), High Diastolic BP (95)"
}
```

---

### 5. Test Case 3: High Sugar Level
**Endpoint:** `POST http://localhost:8080/api/health-records/add/1`

**Body (High Sugar):**
```json
{
  "bloodPressure": "120/80",
  "sugarLevel": 250.0,
  "heartRate": 72
}
```

**Expected Response:**
```json
{
  "id": 3,
  "bloodPressure": "120/80",
  "sugarLevel": 250.0,
  "heartRate": 72,
  "patient": { ... },
  "createdAt": "2026-04-06T21:58:00",
  "status": "High Sugar Level (250.0 mg/dL)"
}
```

---

### 6. Test Case 4: High Heart Rate
**Endpoint:** `POST http://localhost:8080/api/health-records/add/1`

**Body (High HR):**
```json
{
  "bloodPressure": "120/80",
  "sugarLevel": 95.0,
  "heartRate": 130
}
```

**Expected Response:**
```json
{
  "id": 4,
  "bloodPressure": "120/80",
  "sugarLevel": 95.0,
  "heartRate": 130,
  "patient": { ... },
  "createdAt": "2026-04-06T21:59:00",
  "status": "High Heart Rate (130 bpm)"
}
```

---

### 7. Test Case 5: Multiple Abnormalities
**Endpoint:** `POST http://localhost:8080/api/health-records/add/1`

**Body (Multiple issues):**
```json
{
  "bloodPressure": "160/100",
  "sugarLevel": 280.0,
  "heartRate": 125
}
```

**Expected Response:**
```json
{
  "id": 5,
  "bloodPressure": "160/100",
  "sugarLevel": 280.0,
  "heartRate": 125,
  "patient": { ... },
  "createdAt": "2026-04-06T22:00:00",
  "status": "High Systolic BP (160), High Diastolic BP (100), High Sugar Level (280.0 mg/dL), High Heart Rate (125 bpm)"
}
```

---

### 8. Fetch Health Records with Status
**Endpoint:** `GET http://localhost:8080/api/health-records/1?page=0&size=10`

**Expected Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "bloodPressure": "120/80",
      "sugarLevel": 95.0,
      "heartRate": 72,
      "patient": { ... },
      "createdAt": "2026-04-06T21:56:00",
      "status": "Normal"
    },
    {
      "id": 2,
      "bloodPressure": "150/95",
      "sugarLevel": 95.0,
      "heartRate": 72,
      "patient": { ... },
      "createdAt": "2026-04-06T21:57:00",
      "status": "High Systolic BP (150), High Diastolic BP (95)"
    },
    // ... more records
  ],
  "pageable": { ... },
  "totalElements": 5,
  "totalPages": 1
}
```

---

## Health Status Thresholds

| Metric | Normal Range | Alert Threshold |
|--------|--------------|-----------------|
| Systolic BP | ≤ 140 mmHg | > 140 mmHg |
| Diastolic BP | ≤ 90 mmHg | > 90 mmHg |
| Sugar Level | ≤ 200 mg/dL | > 200 mg/dL |
| Heart Rate | ≤ 120 bpm | > 120 bpm |

---

## Status Examples

- **"Normal"** - All values are within normal range
- **"High Systolic BP (150)"** - Only high systolic pressure
- **"High Diastolic BP (95)"** - Only high diastolic pressure
- **"High Sugar Level (250.0 mg/dL)"** - Only high sugar
- **"High Heart Rate (130 bpm)"** - Only high heart rate
- **"High Systolic BP (150), High Diastolic BP (95), High Sugar Level (280.0 mg/dL), High Heart Rate (125 bpm)"** - Multiple issues

---

## Notes
- The `status` field is automatically calculated and stored when a health record is created.
- Each abnormality is listed with its specific measured value for easy reference.
- Multiple abnormalities are comma-separated for clarity.
- The status is persisted in the database and returned in all health record responses.

