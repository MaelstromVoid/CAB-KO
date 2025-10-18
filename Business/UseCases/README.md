# UseCases module - CAB-KO

## Overview

The UseCases module encapsulates the application-specific business rules of the system. It defines interfaces for
interacting with external components such as repositories, external APIs, and message brokers. These interfaces,
referred to as ports, are implemented in the outer layers of the application, adhering to the Dependency Inversion
Principle.

In strict adherence to Clean Architecture, this module would not depend on any framework-specific annotations or
classes. However, to streamline the development process and avoid manual bean declarations for each use case, the module
includes minimal Spring dependencies. This approach maintains the core principle of framework independence while
leveraging Spring's capabilities for dependency injection.

## Key Components

UseCase: Define the application's business operations.

Ports: Interfaces for interactions with external components, ensuring that the core logic remains decoupled from
infrastructure concerns.

UseCaseExecutor: A utility to execute use cases, facilitating the invocation of business operations with appropriate
input and output handling.

## Design Philosophy

While the UseCases module introduces minimal Spring dependencies, it strives to maintain the core principles of Clean
Architecture:

Framework Independence: The core business logic remains decoupled from framework-specific concerns, ensuring flexibility
and ease of testing.

Minimal Spring Usage: Only essential Spring annotations, such as @UseCase, are used to facilitate dependency injection
without compromising the module's independence.

Pure Domain Services: Services from the Core module are instantiated directly within use cases, ensuring that the domain
logic remains pure and free from framework dependencies.