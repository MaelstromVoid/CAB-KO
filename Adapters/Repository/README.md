# Repository Module - CAB-KO

## Overview

The Repository module is responsible for managing database interactions in the CAB-KO backend. It leverages:

- jOOQ for type-safe SQL queries.
- Flyway for database migrations.
- Docker to spin up a PostgreSQL instance during the build process.

This module ensures that the database schema is versioned and consistent, and that the generated code reflects the
current state of the database.

## Structure

src/main/resources/db/migration/: Contains Flyway migration scripts.
target/jooq-codegen/generated/: Contains jOOQ-generated classes and repository implementations.

## Build Configuration

1) Docker PostgreSQL Instance: A PostgreSQL database is started using Docker during the build process.
2) Flyway Migrations: Database migrations are applied to the PostgreSQL instance using Flyway.
3) jOOQ Code Generation: jOOQ generates Java classes from the database schema, providing a type-safe API for
   database interactions.

## Notes & best practices

- Versioned Migrations: Always create new migration scripts for database schema changes. Do not modify existing migration scripts.
- Code Generation: Regenerate jOOQ classes whenever there are changes to the database schema to keep the generated code in sync.