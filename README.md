# Clean Architecture Backend - KOtlin

Backend Project in Clean Architecture using Kotlin, Spring Boot, Maven, jOOQ (and jOOQ code generation), Flyway and JWT
Authentication

## Project Structure

    CAB-KO                     (root module / builder)
    ├─ Adapters/
    │   ├─ Controllers         (module)
    │   ├─ Presenter           (module)
    │   └─ Repository          (module)
    ├─ Business/
    │   ├─ Core                (module)
    │   └─ UseCases            (module)
    ├─ Pom-Parent              (module)
    └─ WebApp                  (module)
    docker-compose-postgresql  (Docker setup for local PostgreSQL)
    README.md                  (you’re reading)
    pom.xml                    (root aggregator POM)

## What this project does

This is a backend application built with:

- Kotlin + Maven
- Spring Boot (for the web and application layer)
- jOOQ (type-safe SQL code generation)
- Flyway (database migrations)
- JWT authentication

It uses a Clean Architecture style separation into modules: domain (Core), application (UseCases),
adapters (Controllers, Presenter, Repository), and the startup module (WebApp).

## Build & Run Instructions

Prerequisites

- Maven 3+
- Java 21
- Docker (for local PostgreSQL)

## Build the full project

From the project root directory: ```mvn clean install```

## Run locally

From the project root directory: ```mvn -pl WebApp spring-boot:run```
This launches the WebApp module which depends on all other modules.

## Access the application

Once started, open: ```http://localhost:8080```

## Accessing the Dockerized Database

- JDBC URL: jdbc:postgresql://localhost:5432/db_cabko
- Username: cabko
- Password: cabko

## Default credentials

Use the following to authenticate:

```
POST http://localhost:8080/api/auth/login

Payload:
{
  "email": "admin@exemple.com",
  "password": "admin"
}
```

You will receive a JWT token. Include it in protected endpoints via header:
```Authorization: Bearer <YOUR_TOKEN>```

## Important Details

- Database migrations (Flyway) are located at: Adapters/Repository/src/main/resources/db/migration/
- The Repository module must be compiled to generate jOOQ code.
- The WebApp module is the executable module; all modules are modularized for separation of concerns.

## Why this architecture

- Clean Architecture ensures that domain logic is isolated from frameworks, making it easier to adapt or evolve the
  system.
- Modules remain loosely coupled, improving testability and maintainability.
- Centralized parent POM simplifies version management and build configuration across modules.

## Next Steps & Documentation

- Each module has its own README.md describing its role, dependencies and how to build/test it.
- For detailed configuration (e.g., JWT settings, database connection), consult application.properties in the WebApp
  module.