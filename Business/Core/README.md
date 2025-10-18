# Core Module - CAB-KO

## Overview

The Core module represents the heart of the application, encapsulating the domain logic and business rules. It defines the essential entities and services that form the foundation of the system. Adhering to Clean Architecture principles, this module is designed to be independent of external frameworks and libraries, ensuring that the core business logic remains pure and unaffected by technological changes.

## Key Components

### Entities

The entities in this module represent the core business objects of the application. They are designed to be simple Kotlin data classes or objects, containing only the necessary attributes and business logic. These entities are agnostic of any persistence or framework-specific annotations, ensuring their portability and testability.

### Domain Services

Domain services encapsulate business logic that doesn't naturally fit within a single entity. These services are implemented as pure Kotlin objects or classes, without any Spring-specific annotations like @Service. This approach ensures that the core logic remains independent and can be easily tested and maintained.

### Design Philosophy

The Core module is intentionally kept lightweight and free from external dependencies. By avoiding annotations such as @Service, the domain services are not managed by the Spring container, allowing them to be used in a static context. This design choice promotes:

- Purity: The business logic remains untainted by framework-specific concerns.
- Testability: The absence of Spring annotations makes the services easier to unit test.
- Flexibility: The core logic can be reused across different contexts without being tied to a specific framework.

For more information on how the Core module interacts with other layers, refer to the documentation of the UseCases module.