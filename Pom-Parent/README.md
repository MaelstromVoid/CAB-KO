# POM-Parent module - CAB-KO

## Overview

This module is the Maven parent for the CAB-KO multi-module backend.
It is a packaging POM that centralizes common build configuration, properties, dependencyManagement and pluginManagement so child modules stay consistent and small.

Think of this project as the single source of truth for:

shared build settings (compiler targets, source directories)

centralized dependency and plugin version management

common plugin configuration and lifecycle bindings

## What this POM provides (high level)

A parent ```<packaging>pom</packaging>``` so every submodule inherits configuration.

Standardized source/test directory layout (Kotlin-friendly locations).

dependencyManagement entries so child modules can declare dependencies without repeating versions.

pluginManagement entries so child modules can use commonly configured plugins (Kotlin compiler, maven-compiler, etc.) with consistent settings.

Minimal "convention over configuration" so each module only contains module-specific code.

## Notes & best practices

- Keep the parent focused on build configuration and version management, avoid leaking module-specific code or heavy runtime dependencies into the parent.
- Use dependencyManagement to pin versions; let children reference artifacts without versions for clarity.
- Keep the parent POM stable. Changing it affects all modules, so coordinate parent changes in a single PR when possible.