# AuthController - Production API Documentation

## Overview
The `AuthController` provides comprehensive authentication services with professional-grade response handling, detailed logging, and Swagger/OpenAPI documentation.

## API Response Structure

All endpoints return a consistent `ApiResponse` wrapper with the following structure:

```json
{
  "code": 200,
  "message": "Success message",
  "status": "SUCCESS",
  "data": { },
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

### Response Fields:
- **code** (int): HTTP status code (200, 201, 400, 404, 500)
- **message** (string): Human-readable message describing the response
- **status** (string): Response status - "SUCCESS" or "ERROR"
- **data** (object): Response payload (null for errors)
- **timestamp** (datetime): Server timestamp in ISO 8601 format
- **path** (string): The request path for audit purposes

---

## Endpoints

### 1. Register New Patient
**POST** `/api/auth/register`

Creates a new patient account with email, name, and password.

#### Request Body
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "SecurePassword@123"
}
```

#### Request Validations
- **name**: Required, minimum 2 characters
- **email**: Required, valid email format, must be unique
- **password**: Required, minimum 8 characters, must contain uppercase, lowercase, number, and special character

#### Success Response (201 Created)
```json
{
  "code": 201,
  "message": "Patient registered successfully. You can now login with your credentials.",
  "status": "SUCCESS",
  "data": {
    "email": "john.doe@example.com",
    "name": "John Doe",
    "message": "Patient registered successfully"
  },
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/register"
}
```

#### Error Responses

**400 Bad Request** - Email already exists
```json
{
  "code": 400,
  "message": "Email already exists",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/register"
}
```

**400 Bad Request** - Validation failed
```json
{
  "code": 400,
  "message": "Validation failed: [field error details]",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/register"
}
```

**500 Internal Server Error**
```json
{
  "code": 500,
  "message": "An unexpected error occurred during registration. Please try again later.",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/register"
}
```

#### HTTP Status Codes
- **201 Created**: Patient registered successfully
- **400 Bad Request**: Invalid input or email already exists
- **500 Internal Server Error**: Unexpected server error

---

### 2. Patient Login
**POST** `/api/auth/login`

Authenticates patient and returns JWT Bearer token valid for 24 hours.

#### Request Body
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword@123"
}
```

#### Request Validations
- **email**: Required, valid email format
- **password**: Required, minimum 8 characters

#### Success Response (200 OK)
```json
{
  "code": 200,
  "message": "Login successful. Use the returned token in the Authorization header as 'Bearer <token>' for subsequent requests.",
  "status": "SUCCESS",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTY0NDI1OTMzNCwiZXhwIjoxNjQ0MzQ1NzM0fQ.AbCdEf1234567890",
    "email": "john.doe@example.com",
    "name": "John Doe",
    "type": "Bearer",
    "expiresIn": 86400000
  },
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

#### Error Responses

**404 Not Found** - User not found
```json
{
  "code": 404,
  "message": "User not found",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

**400 Bad Request** - Invalid credentials
```json
{
  "code": 400,
  "message": "Invalid credentials",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

**400 Bad Request** - Validation failed
```json
{
  "code": 400,
  "message": "Validation failed: [field error details]",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

**500 Internal Server Error**
```json
{
  "code": 500,
  "message": "An unexpected error occurred during login. Please try again later.",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

#### HTTP Status Codes
- **200 OK**: Login successful, JWT token returned
- **400 Bad Request**: Invalid credentials or validation failed
- **404 Not Found**: User not found
- **500 Internal Server Error**: Unexpected server error

#### Token Usage
Include the JWT token in the `Authorization` header for all protected endpoints:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Token Expiration
- Token is valid for **24 hours** (86400000 milliseconds)
- After expiration, user must login again to get a new token

---

## cURL Examples

### Register a new patient
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "SecurePassword@123"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "SecurePassword@123"
  }'
```

### Use JWT Token
```bash
curl -X GET http://localhost:8080/api/health-records \
  -H "Authorization: Bearer <your-jwt-token-here>"
```

---

## Features

### ✅ Production-Level Features

1. **Consistent Response Structure**
   - All endpoints follow the same response format
   - Includes code, message, status, data, timestamp, and path

2. **Comprehensive Error Handling**
   - Try-catch blocks for all exceptions
   - CustomException integration for business logic errors
   - Meaningful error messages for debugging

3. **Detailed Logging**
   - SLF4J logger for audit trail
   - Registration/login attempts logged with outcomes
   - Warnings for validation failures
   - Full error stack traces for debugging

4. **HTTP Status Codes**
   - 201 Created for successful registration
   - 200 OK for successful login
   - 400 Bad Request for validation/business logic errors
   - 404 Not Found when user doesn't exist
   - 500 Internal Server Error for unexpected issues

5. **API Documentation**
   - Swagger/OpenAPI annotations
   - @Tag for endpoint grouping
   - @Operation for descriptions
   - @ApiResponses for all possible outcomes
   - Beautiful auto-generated documentation at `/swagger-ui.html`

6. **Security**
   - Password encryption using BCrypt
   - JWT Bearer tokens for API authentication
   - No sensitive data exposed in responses
   - Request path tracking for audit purposes

7. **Input Validation**
   - @Valid annotation for request body validation
   - Email format validation
   - Password strength requirements
   - Unique email constraint

8. **Request Context Tracking**
   - Request path included in response
   - Timestamp for all responses
   - Helps with debugging and monitoring

---

## Integration with Other Endpoints

All protected endpoints require JWT token in the Authorization header:

```
Authorization: Bearer <token-from-login-response>
```

### Example: Fetch Health Records (Protected)
```bash
curl -X GET http://localhost:8080/api/health-records \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

---

## Swagger UI

Access the auto-generated API documentation at:
```
http://localhost:8080/swagger-ui.html
```

This provides:
- Interactive API testing
- Request/response examples
- Parameter descriptions
- All HTTP methods and status codes

---

## Response Status Codes Summary

| Code | Name | Description |
|------|------|-------------|
| 200 | OK | Login successful |
| 201 | Created | Registration successful |
| 400 | Bad Request | Invalid input or duplicate email |
| 404 | Not Found | User not found during login |
| 500 | Internal Server Error | Unexpected server error |

---

## Data Transfer Objects (DTOs)

### RegisterRequest
```java
{
  "name": "string",
  "email": "string",
  "password": "string"
}
```

### RegisterResponse
```java
{
  "email": "string",
  "name": "string",
  "message": "string"
}
```

### LoginRequest
```java
{
  "email": "string",
  "password": "string"
}
```

### AuthResponse
```java
{
  "token": "string",
  "email": "string",
  "name": "string",
  "type": "string",      // Always "Bearer"
  "expiresIn": "number"  // Milliseconds (86400000 = 24 hours)
}
```

### ApiResponse<T>
```java
{
  "code": "number",
  "message": "string",
  "status": "string",    // "SUCCESS" or "ERROR"
  "data": "object",      // Generic data payload
  "timestamp": "datetime",
  "path": "string"
}
```

---

## Version Information
- **API Version**: 1.0
- **Spring Boot**: 3.2.5
- **Java**: 17+
- **JWT Expiration**: 24 hours

---

## Support

For API issues or questions:
1. Check the Swagger UI documentation: `http://localhost:8080/swagger-ui.html`
2. Review the logs for detailed error information
3. Verify request format matches the examples provided
4. Ensure JWT token is included in Authorization header for protected endpoints

