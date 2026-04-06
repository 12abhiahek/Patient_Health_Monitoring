# AuthController - Quick Start Guide

## 🎯 Overview

Your `AuthController` is now **production-ready** with:
- ✅ Proper response objects and status codes
- ✅ Comprehensive error handling
- ✅ Detailed logging
- ✅ Swagger/OpenAPI documentation
- ✅ JWT token authentication
- ✅ Professional code structure

---

## 🚀 Quick Start

### 1. Start the Application
```bash
java -jar target/PatientHealthMonitoring-0.0.1-SNAPSHOT.jar
```

Server runs on: `http://localhost:8080`

### 2. View API Documentation
Open in browser: `http://localhost:8080/swagger-ui.html`

---

## 📡 API Endpoints

### Register a Patient
```bash
POST /api/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "Password@123"
}
```

**Response (201 Created):**
```json
{
  "code": 201,
  "message": "Patient registered successfully. You can now login with your credentials.",
  "status": "SUCCESS",
  "data": {
    "email": "john@example.com",
    "name": "John Doe",
    "message": "Patient registered successfully"
  },
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/register"
}
```

---

### Login Patient
```bash
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "Password@123"
}
```

**Response (200 OK):**
```json
{
  "code": 200,
  "message": "Login successful. Use the returned token in the Authorization header as 'Bearer <token>' for subsequent requests.",
  "status": "SUCCESS",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "email": "john@example.com",
    "name": "John Doe",
    "type": "Bearer",
    "expiresIn": 86400000
  },
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

---

### Use JWT Token in Protected Endpoints
```bash
GET /api/health-records
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 📊 HTTP Status Codes

| Code | Endpoint | Meaning |
|------|----------|---------|
| 201 | POST /register | Patient registered successfully |
| 200 | POST /login | Login successful, token returned |
| 400 | Either | Email exists / Invalid credentials / Validation failed |
| 404 | POST /login | User not found |
| 500 | Either | Unexpected server error |

---

## 🔑 Response Status Values

- **"SUCCESS"** → Request succeeded
- **"ERROR"** → Request failed (check message for details)

---

## 🛡️ Security

- Password encrypted with BCrypt
- JWT tokens with 24-hour expiration
- Unique email constraint
- CORS enabled
- Spring Security integration

---

## 📝 Response Structure

Every API response follows this format:

```json
{
  "code": 200,              // HTTP status code
  "message": "...",         // Human-readable message
  "status": "SUCCESS",      // "SUCCESS" or "ERROR"
  "data": { },              // Response payload (null for errors)
  "timestamp": "...",       // Server timestamp
  "path": "/api/auth/..."   // Request path
}
```

---

## ✅ Error Handling Examples

### Duplicate Email
**Status**: 400 Bad Request
```json
{
  "code": 400,
  "message": "Email already exists",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/register"
}
```

### User Not Found
**Status**: 404 Not Found
```json
{
  "code": 404,
  "message": "User not found",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

### Invalid Credentials
**Status**: 400 Bad Request
```json
{
  "code": 400,
  "message": "Invalid credentials",
  "status": "ERROR",
  "timestamp": "2026-04-06T23:35:34.123456",
  "path": "/api/auth/login"
}
```

---

## 🧪 Testing with cURL

### Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "Password@123"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "Password@123"
  }'
```

### Use Token (Replace TOKEN with actual token)
```bash
curl -X GET http://localhost:8080/api/health-records \
  -H "Authorization: Bearer TOKEN"
```

---

## 📚 Testing with Postman

1. **Create Register Request**
   - Method: POST
   - URL: `http://localhost:8080/api/auth/register`
   - Body (JSON):
   ```json
   {
     "name": "John Doe",
     "email": "john@example.com",
     "password": "Password@123"
   }
   ```

2. **Create Login Request**
   - Method: POST
   - URL: `http://localhost:8080/api/auth/login`
   - Body (JSON):
   ```json
   {
     "email": "john@example.com",
     "password": "Password@123"
   }
   ```
   - Copy the `token` from response

3. **Use Token in Protected Endpoint**
   - Method: GET
   - URL: `http://localhost:8080/api/health-records`
   - Headers:
     - Key: `Authorization`
     - Value: `Bearer <TOKEN_FROM_LOGIN>`

---

## 🔍 Features Overview

### Response Handling
- ✅ Consistent structure for all endpoints
- ✅ Proper HTTP status codes
- ✅ Detailed error messages
- ✅ Timestamp for all responses
- ✅ Request path for debugging

### Error Management
- ✅ Try-catch blocks
- ✅ CustomException integration
- ✅ Meaningful messages
- ✅ Stack traces in logs

### Logging
- ✅ Registration attempts logged
- ✅ Login attempts logged
- ✅ Failures warned
- ✅ Errors with stack traces

### Documentation
- ✅ Swagger/OpenAPI annotations
- ✅ Auto-generated UI documentation
- ✅ JavaDoc comments
- ✅ This guide

### Security
- ✅ BCrypt password encryption
- ✅ JWT Bearer tokens
- ✅ 24-hour token expiration
- ✅ Email uniqueness validation
- ✅ Spring Security integration

---

## 📂 Files Overview

```
PatientHealthMonitoring/
├── src/main/java/.../controller/
│   └── AuthController.java          ✅ Production-ready
├── src/main/java/.../service/
│   └── AuthService.java             ✅ Updated with logging
├── src/main/java/.../dto/
│   ├── ApiResponse.java             ✅ NEW - Generic wrapper
│   ├── AuthResponse.java            ✅ NEW - Login response
│   ├── RegisterResponse.java        ✅ NEW - Register response
│   ├── LoginRequest.java            ✅ Existing
│   └── RegisterRequest.java         ✅ Existing
├── target/
│   └── PatientHealthMonitoring-0.0.1-SNAPSHOT.jar  ✅ Ready to deploy
├── AUTH_API_DOCUMENTATION.md        ✅ Complete API docs
└── QUICKSTART.md                    ✅ This file
```

---

## 🎯 Production Features Implemented

✅ **REST API Standards**
- Proper HTTP methods and status codes
- RESTful endpoint design
- Consistent resource representation

✅ **Response Design**
- Generic wrapper pattern
- Null-safe JSON serialization
- Timestamp tracking
- Request context in response

✅ **Error Handling**
- Meaningful error messages
- Appropriate status codes
- No stack trace leakage
- Consistent error format

✅ **Security**
- Password encryption
- JWT authentication
- CORS protection
- Spring Security integration

✅ **Logging & Monitoring**
- Audit trail for all operations
- Error stack traces
- Warning for business logic issues
- Request/response tracking

✅ **Documentation**
- Swagger/OpenAPI support
- JavaDoc comments
- This quick start guide
- Complete API documentation

✅ **Code Quality**
- Type-safe implementations
- Builder pattern
- Dependency injection
- Clean code principles

---

## 🚨 Common Issues & Solutions

### Issue: "Email already exists"
**Solution**: Use a different email address or register with a new account

### Issue: "Invalid credentials"
**Solution**: Verify email and password are correct. Passwords are case-sensitive.

### Issue: "User not found"
**Solution**: Register a new account first before attempting to login

### Issue: Token expired
**Solution**: Login again to get a new token. Tokens expire after 24 hours.

### Issue: "Unauthorized" in protected endpoints
**Solution**: Ensure JWT token is included in Authorization header as `Bearer <token>`

---

## 📞 Support Resources

1. **Swagger UI**: `http://localhost:8080/swagger-ui.html`
2. **API Documentation**: `AUTH_API_DOCUMENTATION.md`
3. **Application Logs**: Check console output for detailed information
4. **Source Code**: Well-documented with JavaDoc comments

---

## 🎓 Learning Points

This implementation demonstrates:
- Spring Boot REST API development
- JWT authentication & security
- Proper HTTP status code usage
- Professional response design
- Error handling best practices
- Logging & monitoring
- API documentation standards
- Testing & validation

---

**Status**: ✅ PRODUCTION READY

Compiled, tested, and ready for deployment!

