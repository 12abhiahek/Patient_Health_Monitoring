# Patient Health Monitoring System

A comprehensive RESTful backend application built with Spring Boot that enables patients to record daily health data and provides basic alert generation for abnormal health values.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Database Schema](#database-schema)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

The Patient Health Monitoring System is designed to help patients track their daily health metrics and receive alerts when their readings fall outside normal ranges. The system provides a secure, RESTful API that supports patient registration, authentication, health data recording, and retrieval of health records with pagination support.

### Key Components

- **Authentication System**: JWT-based authentication for secure patient login and registration
- **Health Data Management**: Recording and retrieving blood pressure, sugar levels, and heart rate
- **Alert System**: Automatic detection of abnormal health values with status indicators
- **RESTful APIs**: Well-documented endpoints following REST principles
- **Layered Architecture**: Clean separation of concerns with Controller, Service, and Repository layers

## Features

### Core Features
- ✅ Patient registration and login with JWT authentication
- ✅ Health data entry (Blood Pressure, Sugar Level, Heart Rate)
- ✅ Fetch patient health records with pagination
- ✅ Alert logic for abnormal health values
- ✅ Proper layered architecture (Controller, Service, Repository)
- ✅ MySQL database integration

### Bonus Features
- ✅ Pagination for health records
- ✅ Input validation and exception handling
- ✅ JWT-based authentication
- ✅ Swagger/OpenAPI documentation
- ✅ Production-ready error responses
- ✅ Health status indicators (Normal, High Blood Pressure, High Sugar Level, High Heart Rate)

## Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication and authorization
- **Spring Validation** - Input validation
- **JWT (JJWT)** - Token-based authentication
- **MySQL** - Relational database
- **Lombok** - Code generation
- **SpringDoc OpenAPI** - API documentation

### Development Tools
- **Maven** - Dependency management and build tool
- **JUnit 5** - Unit testing
- **Spring Boot Test** - Integration testing

## Database Schema

### Patients Table
```sql
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

### Health Records Table
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

### Entity Relationships
- **One-to-Many**: Patient → Health Records (One patient can have multiple health records)

## Prerequisites

Before running this application, make sure you have the following installed:

- **Java 17** or higher
- **MySQL 8.0** or higher
- **Maven 3.6+**
- **Git**

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd PatientHealthMonitoring
```

### 2. Database Setup
Create a MySQL database named `patient_health`:
```sql
CREATE DATABASE patient_health;
```

### 3. Configure Database Connection
Update the database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/patient_health?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Build the Application
```bash
mvn clean install
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Configuration

### Application Properties
Key configuration options in `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/patient_health?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080

# JWT Configuration (if applicable)
# jwt.secret=your-secret-key
# jwt.expiration=86400000
```

### Environment Variables
For production deployment, consider using environment variables:
```bash
export DB_URL=jdbc:mysql://localhost:3306/patient_health
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export JWT_SECRET=your-secret-key
```

## API Documentation

### Swagger UI
Access the interactive API documentation at:
```
http://localhost:8080/swagger-ui.html
```

### OpenAPI Specification
Access the OpenAPI JSON specification at:
```
http://localhost:8080/api-docs
```

### Main Endpoints

#### Authentication
- `POST /api/auth/register` - Register a new patient
- `POST /api/auth/login` - Patient login

#### Health Records
- `POST /api/health-records` - Create health record
- `GET /api/health-records` - Get patient's health records (paginated)
- `GET /api/health-records/{id}` - Get specific health record

#### Patient Management
- `GET /api/patients/{id}` - Get patient details

## Usage

### 1. Register a Patient
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123"
  }'
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "password123"
  }'
```

### 3. Add Health Record
```bash
curl -X POST http://localhost:8080/api/health-records \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "bloodPressure": "120/80",
    "sugarLevel": 95.0,
    "heartRate": 72
  }'
```

### 4. Get Health Records
```bash
curl -X GET "http://localhost:8080/api/health-records?page=0&size=10" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

### Test Coverage
```bash
mvn test jacoco:report
```

## Deployment

### JAR Build
```bash
mvn clean package
java -jar target/PatientHealthMonitoring-0.0.1-SNAPSHOT.jar
```

### Docker Deployment (Optional)
Create a `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-alpine
COPY target/PatientHealthMonitoring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Build and run:
```bash
docker build -t patient-health-monitoring .
docker run -p 8080:8080 patient-health-monitoring
```

## Health Alert Logic

The system automatically detects abnormal health values:

- **Blood Pressure**: Normal range 90/60 - 140/90 mmHg
- **Sugar Level**: Normal range 70-140 mg/dL (fasting)
- **Heart Rate**: Normal range 60-100 bpm

Status indicators:
- `Normal` - All values within normal range
- `High Blood Pressure` - Systolic > 140 or Diastolic > 90
- `Low Blood Pressure` - Systolic < 90 or Diastolic < 60
- `High Sugar Level` - Sugar > 140
- `Low Sugar Level` - Sugar < 70
- `High Heart Rate` - Heart rate > 100
- `Low Heart Rate` - Heart rate < 60

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Standards
- Follow Java naming conventions
- Use meaningful variable and method names
- Add appropriate JavaDoc comments
- Write unit tests for new features
- Ensure code coverage > 80%

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the API documentation for endpoint details

---

**Note**: This is a development version. For production use, ensure proper security configurations, database optimizations, and monitoring are in place.
