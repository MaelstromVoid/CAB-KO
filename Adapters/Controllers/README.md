# Controllers Module - CAB-KO

## Overview

The Controllers module serves as the entry point for HTTP requests in the application. It is responsible for:

- Receiving and processing HTTP requests.
- Coordinating interactions between the client and the application's core logic.
- Returning appropriate HTTP responses.

This module acts as the interface between the external world (e.g., web clients, mobile applications) and the internal
application layers.

## Structure

The Controllers module typically includes:

- Controller Classes: These classes handle incoming HTTP requests and delegate the processing to the appropriate
  services or use cases.
- Request Mappings: Methods within controller classes are annotated with mappings like @GetMapping, @PostMapping, etc.,
  to define the endpoints.
- Response Handling: Controllers prepare and return HTTP responses, often using DTOs (Data Transfer Objects) defined in
  the Presenter module.

## Integration with Other Modules

- Use Cases: Controllers delegate business logic to the UseCases module, ensuring that the core domain logic remains
  separate from the web layer.
- Presenter: Controllers utilize DTOs from the Presenter module to structure the data exchanged with clients.
- WebApp: While the Controllers module defines the necessary interfaces (e.g., JwtTokenProvider), the actual
  implementations and configurations are provided in the WebApp module.