# Bank Microservices README

This repository contains a **Banking System** built using a **Microservices Architecture**, consisting of three independent services:

* **Accounts Service**
* **Cards Service**
* **Loans Service**

Each microservice follows industry-standard best practices such as global exception handling, input validation, logging, OpenAPI integration, and containerization.

---

## 📌 Architecture Overview

The system is designed following **microservice** principles:

* Each service has its own **data model**, **database**, and **domain logic**.
* Services communicate via **REST APIs**.
* Uses **Spring Boot**, **Spring Validation**, **Spring Cloud**, **Spring Data JPA**, and **OpenAPI** for API documentation.
* Common practices implemented: exception handling, logging, DTOs, validation, config separation.

---

## 🚀 Microservices

### 1. Accounts Microservice

Responsible for managing customer accounts.

**Features:**

* Create, update, retrieve account details
* Stores customer personal & banking info
* Validation rules applied at DTO level
* Exposes REST APIs documented via OpenAPI

**Tech Stack:** Spring Boot, JPA, PostgreSQL/MySQL

---

### 2. Cards Microservice

Handles customer card-related operations.

**Features:**

* Issue new cards
* Track card limits and activity
* API documentation with OpenAPI
* Input validation and exception safety

**Tech Stack:** Spring Boot, JPA, PostgreSQL/MySQL

---

### 3. Loans Microservice

Supports loan creation and status tracking.

**Features:**

* Loan assignment
* Loan repayment info
* DTO validations + exception handling
* OpenAPI integration

**Tech Stack:** Spring Boot, JPA, PostgreSQL/MySQL

---

## 🛠 Best Practices Implemented

### ✔ 1. Global Exception Handling

Each service has its own **@ControllerAdvice** class to handle exceptions:

* Custom exceptions (e.g., ResourceNotFoundException)
* Validation errors
* Internal server errors

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .toList();
        return new ResponseEntity<>(new ErrorResponse(errors.toString()), HttpStatus.BAD_REQUEST);
    }
}
```

---

### ✔ 2. Request Validation (DTO Level)

Each incoming request is validated using **Hibernate Validator**.

```java
public class AccountDTO {
    @NotBlank(message = "Customer name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;
}
```

Validation errors automatically trigger global handler.

---

### ✔ 3. OpenAPI / Swagger Integration

Every service contains Swagger configuration.

* API docs at: `/swagger-ui/index.html`
* OpenAPI JSON at: `/v3/api-docs`

```java
@OpenAPIDefinition(
    info = @Info(title = "Accounts API", version = "1.0", description = "Account Service Documentation")
)
@SpringBootApplication
public class AccountsApplication {}
```

---

### ✔ 4. Layered Architecture

All services follow:

```
controller → service → repository → entity
```

* **Controller** handles request & response mapping
* **Service** contains business logic
* **Repository** interacts with the database
* **DTOs** used for input/output, not exposing entities directly

---

### ✔ 5. Logging (SLF4J)

Each service includes structured logging.

```java
private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

logger.info("Creating account for {}", accountDTO.getName());
```

---

### ✔ 6. Config Management

Environment-specific configuration files:

```
application-dev.yml
application-prod.yml
```

Database credentials and secrets stored via **environment variables**.

---

## 📂 Project Structure (example)

```
/accounts
  ├── src/main/java/com/bank/accounts
  │   ├── controller
  │   ├── service
  │   ├── repository
  │   ├── entity
  │   ├── dto
  │   └── exception
  ├── application.yml

/cards
/loans
```
---

## 🐳 Docker Support

All services have `Dockerfile` + `docker-compose.yml` to run locally.

```
docker-compose up --build
```

---

## 🏁 How to Run Locally

1. Clone the repository:

```
git clone https://github.com/zsanjay/Bank-Microservice.git
```

2. Start individual services:

```
cd accounts
./mvnw spring-boot:run
```

3. Access API documentation:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📄 License

This project is licensed under the MIT License.

---

## 💬 Contact

For any questions or improvements:

* GitHub Issues: Open a ticket
