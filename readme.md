# Flight Management System (FMS)

## Overview

This project is a full-stack web application developed as part of the **Advanced Programming Methods (Metode Avansate de Programare – MAP)** course at **Babeș-Bolyai University (UBB)**.

The application models and manages core airline and airport operations, including flights, aircraft, passengers, tickets, crew assignments, and luggage tracking. 
It enforces strict business rules, supports dynamic filtering and sorting, and uses a relational database for persistence.

The project was developed collaboratively by:

- Ana Chialda
- Andreea Maria Badiu  


---

## Architecture

The system follows a layered architecture:

- **Controller Layer** – Handles HTTP requests and response rendering.
- **Service Layer** – Contains business logic, validation, and rule enforcement.
- **Repository Layer** – Data access abstraction using Spring Data JPA.
- **Domain Layer** – Entity modeling and relationships.
- **View Layer** – Server-side rendering with Thymeleaf templates.

Key architectural principles applied:

- Separation of concerns  
- Repository pattern  
- Specification pattern (JPA Criteria API)  
- Service-layer business rule enforcement  
- Clear distinction between database-level and in-memory operations  

---

## Core Functionality

### CRUD Operations

The system provides full CRUD support for nine interconnected entities:

- Flight  
- Airplane  
- Passenger  
- Ticket  
- Luggage  
- AirlineEmployee  
- AirportEmployee  
- NoticeBoard  
- FlightAssignment  

These entities are connected through various relationships, including One-to-Many associations and inheritance hierarchies (e.g., AirlineEmployee and AirportEmployee).

---

### Dynamic Filtering

Filtering is implemented using **JPA Specifications (Criteria API)**, allowing the construction of dynamic, strongly-typed queries based on user input.

Users can combine multiple parameters such as:

- Text-based search  
- Date ranges  
- Enum values  
- Numeric intervals  

Filtering logic is handled at the repository level to ensure performance and maintain separation of concerns.

---

### Sorting

Two sorting strategies are implemented:

1. **Database-level sorting** using Spring Data `Sort`.
2. **Custom in-memory sorting** using Java `Comparator` logic in the Service layer.

The second approach is used when sorting by dynamically computed fields, such as:

- Number of tickets per passenger  
- Number of flights per airplane  

This ensures correctness when the database cannot directly compute or sort by derived values.

---

## Validation and Business Rules

The application enforces both structural validation and domain-specific business constraints.

### Structural Validation

- Required fields must be present.  
- Invalid entities cannot be persisted.  
- Validation errors are returned to the UI and displayed clearly.  

### Business Rules

Examples of enforced constraints:

- A flight cannot have an arrival time earlier than its departure time.  
- Seats cannot be double-booked.  
- A passenger with active future tickets cannot be deleted.  
- Luggage must be associated with an existing ticket.  

Business validation is performed in the Service layer to maintain domain consistency.

---

## Exception Handling

Global exception handling is implemented using `@ControllerAdvice`.

When business rules are violated:

- Exceptions are intercepted centrally.  
- The user is redirected appropriately.  
- Error messages are displayed using `RedirectAttributes`.  
- The application flow remains stable.  

This prevents application crashes and ensures a consistent user experience.

---

## Persistence

The application uses **MySQL (local server)** for data storage.

If the database is empty at startup, a `CommandLineRunner` automatically seeds it with realistic test data, including:

- Airplane models  
- Flight codes  
- Passenger names  
- Staff assignments  etc.

This allows immediate testing of all functionalities without manual setup.

Note: The database is local. Data persists only within the configured MySQL instance.

---

## Technology Stack

**Backend**
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Validation

**Database**
- MySQL (local development)

**Frontend**
- Thymeleaf (server-side templating)
- Bootstrap 5
- HTML5 / CSS3

**Build Tool**
- Maven

---

## Installation and Execution

### Prerequisites

- Java 17 or higher  
- Maven  
- MySQL Server running locally  

### Database Setup

Create the database:

sql
CREATE DATABASE flight_db; 

Configure application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/flight_db?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect


Run the Application

Build and start the application:

mvn clean install
mvn spring-boot:run


Access the application in your browser:

http://localhost:8080


Design Considerations

- This project focuses on:
- Modeling complex relational data
- Applying dynamic query construction
- Maintaining UI state across HTTP GET requests
- Separating database logic from business logic
- Enforcing domain rules in a maintainable manner
- Combining database-level and application-level sorting

The goal was not only to implement CRUD operations, but to build a system that reflects real-world operational constraints and consistent domain logic.
