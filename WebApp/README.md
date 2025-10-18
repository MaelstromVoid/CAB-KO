# WebApp Module - CAB-KO

## Overview

The WebApp module serves as the entry point for the CAB-KO application. It is responsible for initializing and
bootstrapping the application, integrating all necessary modules, and exposing the application as a Spring Boot
application. This module is tightly coupled with Spring Boot and Spring Security.

## Features

Spring Boot Integration: Bootstraps the application using Spring Boot's auto-configuration and embedded server
capabilities.

Security Configuration: Integrates Spring Security for authentication and authorization mechanisms.

Modular Architecture: Depends on other CAB-KO modules such as Core, UseCases, Repository, Presenter, and Controllers to
form a cohesive application.

Docker Support: Includes configurations for running the application in a Docker container.

