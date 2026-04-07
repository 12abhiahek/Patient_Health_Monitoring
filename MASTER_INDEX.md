# Patient Health Monitoring System - Master Index

## Table of Contents
- [Project Overview](#project-overview)
- [Source Code Index](#source-code-index)
- [Configuration Files](#configuration-files)
- [Documentation Index](#documentation-index)
- [Test Files Index](#test-files-index)
- [Build & Deployment Files](#build--deployment-files)
- [API Reference](#api-reference)
- [Database Reference](#database-reference)
- [Quick Navigation](#quick-navigation)

## Project Overview

**Project Name:** Patient Health Monitoring System
**Version:** 1.0.0
**Technology:** Spring Boot 3.2.5, Java 17, MySQL
**Architecture:** Layered Architecture (Controller → Service → Repository)
**Last Updated:** April 7, 2026

---

## Source Code Index

### Main Application
| File | Location | Description |
|------|----------|-------------|
| `PatientHealthMonitoringApplication.java` | `src/main/java/com/patient/PatientHealthMonitoring/` | Main Spring Boot application class |

### Configuration Layer
| File | Location | Description |
|------|----------|-------------|
| `SecurityConfig.java` | `src/main/java/com/patient/PatientHealthMonitoring/config/` | Spring Security configuration |
| `JwtAuthenticationFilter.java` | `src/main/java/com/patient/PatientHealthMonitoring/config/` | JWT authentication filter |

### Controller Layer
| File | Location | Description |
|------|----------|-------------|
| `AuthController.java` | `src/main/java/com/patient/PatientHealthMonitoring/controller/` | Authentication endpoints (register/login) |
| `HealthRecordController.java` | `src/main/java/com/patient/PatientHealthMonitoring/controller/` | Health record CRUD operations |
| `PatientController.java` | `src/main/java/com/patient/PatientHealthMonitoring/controller/` | Patient management endpoints |

### Service Layer
| File | Location | Description |
|------|----------|-------------|
| `AuthService.java` | `src/main/java/com/patient/PatientHealthMonitoring/service/` | Authentication business logic |
| `HealthRecordService.java` | `src/main/java/com/patient/PatientHealthMonitoring/service/` | Health record business logic |
| `PatientService.java` | `src/main/java/com/patient/PatientHealthMonitoring/service/` | Patient management business logic |

### Repository Layer
| File | Location | Description |
|------|----------|-------------|
| `PatientRepository.java` | `src/main/java/com/patient/PatientHealthMonitoring/repository/` | Patient data access operations |
| `HealthRecordRepository.java` | `src/main/java/com/patient/PatientHealthMonitoring/repository/` | Health record data access operations |

### Entity Layer
| File | Location | Description |
|------|----------|-------------|
| `Patient.java` | `src/main/java/com/patient/PatientHealthMonitoring/entity/` | Patient JPA entity |
| `HealthRecord.java` | `src/main/java/com/patient/PatientHealthMonitoring/entity/` | Health record JPA entity |

### DTO Layer
| File | Location | Description |
|------|----------|-------------|
| `ApiResponse.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Generic API response wrapper |
| `AuthResponse.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Authentication response DTO |
| `LoginRequest.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Login request DTO |
| `RegisterRequest.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Registration request DTO |
| `RegisterResponse.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Registration response DTO |
| `HealthRecordRequest.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Health record creation request |
| `HealthRecordResponse.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Health record response DTO |
| `PatientResponse.java` | `src/main/java/com/patient/PatientHealthMonitoring/dto/` | Patient details response |

### Exception Layer
| File | Location | Description |
|------|----------|-------------|
| `CustomException.java` | `src/main/java/com/patient/PatientHealthMonitoring/exception/` | Base custom exception class |
| `GlobalExceptionHandler.java` | `src/main/java/com/patient/PatientHealthMonitoring/exception/` | Global exception handling |

---

## Configuration Files

| File | Location | Description |
|------|----------|-------------|
| `application.properties` | `src/main/resources/` | Main application configuration |
| `pom.xml` | `root/` | Maven project configuration and dependencies |

---

## Documentation Index

| Document | Location | Description |
|----------|----------|-------------|
| `README.md` | `root/` | Project overview, setup, and usage guide |
| `DELIVERY_CHECKLIST.md` | `root/` | Delivery checklist and sign-off requirements |
| `IMPLEMENTATION_REFERENCE.md` | `root/` | Technical implementation details and architecture |
| `MASTER_INDEX.md` | `root/` | This master index file |
| `QUICKSTART.md` | `root/` | Quick start guide for developers |
| `HELP.md` | `root/` | Additional help and troubleshooting |
| `VISUAL_SUMMARY.md` | `root/` | Visual project summary |
| `PRODUCTION_AUTH_IMPROVEMENTS.md` | `root/` | Production authentication improvements |
| `AUTH_API_DOCUMENTATION.md` | `root/` | Authentication API documentation |
| `HEALTH_RECORD_STATUS_TESTING_GUIDE.md` | `root/` | Health record status testing guide |

---

## Test Files Index

| File | Location | Description |
|------|----------|-------------|
| `PatientHealthMonitoringApplicationTests.java` | `src/test/java/com/patient/PatientHealthMonitoring/` | Main application context test |
| `HealthRecordControllerTest.java` | `src/test/java/com/patient/PatientHealthMonitoring/` | Health record controller tests |

---

## Build & Deployment Files

| File | Location | Description |
|------|----------|-------------|
| `pom.xml` | `root/` | Maven build configuration |
| `mvnw` | `root/` | Maven wrapper for Unix/Linux |
| `mvnw.cmd` | `root/` | Maven wrapper for Windows |
| `.gitignore` | `root/` | Git ignore patterns |
| `.gitattributes` | `root/` | Git attributes configuration |

---

## API Reference

### Authentication Endpoints
| Method | Endpoint | Description | Controller |
|--------|----------|-------------|------------|
| `POST` | `/api/auth/register` | Register new patient | `AuthController` |
| `POST` | `/api/auth/login` | Patient login | `AuthController` |

### Health Record Endpoints
| Method | Endpoint | Description | Controller |
|--------|----------|-------------|------------|
| `POST` | `/api/health-records` | Create health record | `HealthRecordController` |
| `GET` | `/api/health-records` | Get paginated health records | `HealthRecordController` |
| `GET` | `/api/health-records/{id}` | Get specific health record | `HealthRecordController` |

### Patient Endpoints
| Method | Endpoint | Description | Controller |
|--------|----------|-------------|------------|
| `GET` | `/api/patients/{id}` | Get patient details | `PatientController` |

### Documentation Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/swagger-ui.html` | Swagger UI documentation |
| `GET` | `/api-docs` | OpenAPI specification |

---

## Database Reference

### Tables
| Table | Description | Primary Key |
|-------|-------------|-------------|
| `patients` | Patient account information | `id` (BIGINT) |
| `health_records` | Health measurement records | `id` (BIGINT) |

### Table Schemas

#### patients
```sql
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

#### health_records
```sql
CREATE TABLE health_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    blood_pressure VARCHAR(255),
    sugar_level DOUBLE,
    heart_rate INT,
    patient_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    status VARCHAR(255),
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);
```

### Indexes
- Primary key indexes on `id` columns
- Foreign key index on `health_records.patient_id`
- Unique index on `patients.email`

---

## Quick Navigation

### By Functionality
- **Authentication**: `AuthController.java`, `AuthService.java`, `SecurityConfig.java`
- **Health Records**: `HealthRecordController.java`, `HealthRecordService.java`, `HealthRecord.java`
- **Patient Management**: `PatientController.java`, `PatientService.java`, `Patient.java`
- **Security**: `JwtAuthenticationFilter.java`, `SecurityConfig.java`
- **Error Handling**: `GlobalExceptionHandler.java`, `CustomException.java`

### By Layer
- **Controller**: All files in `controller/` package
- **Service**: All files in `service/` package
- **Repository**: All files in `repository/` package
- **Entity**: All files in `entity/` package
- **DTO**: All files in `dto/` package
- **Configuration**: All files in `config/` package

### By Technology
- **Spring Boot**: `PatientHealthMonitoringApplication.java`, `pom.xml`
- **Spring Security**: `SecurityConfig.java`, `JwtAuthenticationFilter.java`
- **Spring Data JPA**: All files in `repository/` and `entity/` packages
- **JWT**: `JwtAuthenticationFilter.java`, `AuthService.java`
- **Validation**: All controller and DTO files
- **Documentation**: `pom.xml` (SpringDoc), controller annotations

### Common Operations
- **User Registration**: `AuthController.register()` → `AuthService.register()` → `PatientRepository.save()`
- **User Login**: `AuthController.login()` → `AuthService.login()` → JWT token generation
- **Create Health Record**: `HealthRecordController.create()` → `HealthRecordService.create()` → `HealthRecordRepository.save()`
- **Get Health Records**: `HealthRecordController.getAll()` → `HealthRecordService.getAll()` → `HealthRecordRepository.findAll()`

### Configuration Properties
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/patient_health
spring.datasource.username=root
spring.datasource.password=abhi@18
spring.jpa.hibernate.ddl-auto=update

# Server
server.port=8080

# Security (JWT)
jwt.secret=your-secret-key
jwt.expiration=86400000

# Documentation
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Health Status Logic
- **Normal**: All values within normal ranges
- **High Blood Pressure**: Systolic > 140 or Diastolic > 90
- **Low Blood Pressure**: Systolic < 90 or Diastolic < 60
- **High Sugar Level**: Sugar > 140
- **Low Sugar Level**: Sugar < 70
- **High Heart Rate**: Heart rate > 100
- **Low Heart Rate**: Heart rate < 60

### Dependencies (Key)
- `spring-boot-starter-web`: Web framework
- `spring-boot-starter-data-jpa`: Data persistence
- `spring-boot-starter-security`: Security
- `spring-boot-starter-validation`: Input validation
- `mysql-connector-j`: MySQL driver
- `jjwt-api`: JWT tokens
- `springdoc-openapi-starter-webmvc-ui`: API documentation
- `lombok`: Code generation

---

## File Relationships Map

```
PatientHealthMonitoringApplication.java
├── config/
│   ├── SecurityConfig.java
│   └── JwtAuthenticationFilter.java
├── controller/
│   ├── AuthController.java
│   │   └── AuthService.java
│   ├── HealthRecordController.java
│   │   └── HealthRecordService.java
│   └── PatientController.java
│       └── PatientService.java
├── service/
│   ├── AuthService.java
│   │   └── PatientRepository.java
│   ├── HealthRecordService.java
│   │   └── HealthRecordRepository.java
│   └── PatientService.java
│       └── PatientRepository.java
├── repository/
│   ├── PatientRepository.java
│   │   └── Patient.java
│   └── HealthRecordRepository.java
│       └── HealthRecord.java
├── entity/
│   ├── Patient.java
│   └── HealthRecord.java
├── dto/
│   ├── ApiResponse.java
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   ├── RegisterResponse.java
│   ├── HealthRecordRequest.java
│   ├── HealthRecordResponse.java
│   └── PatientResponse.java
└── exception/
    ├── CustomException.java
    └── GlobalExceptionHandler.java
```

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | April 7, 2026 | Initial release with core features |

---

## Contact & Support

For questions or support:
- Check the `HELP.md` file
- Review `IMPLEMENTATION_REFERENCE.md` for technical details
- See `README.md` for setup and usage instructions

---

**Index Generated:** April 7, 2026
**Total Files:** 25+ source files, 10+ documentation files
**Lines of Code:** ~2000+ (estimated)
