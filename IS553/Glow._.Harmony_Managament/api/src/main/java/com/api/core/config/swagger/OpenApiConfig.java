package com.api.core.config.swagger;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuration class for setting up OpenAPI documentation for the 
 * Glow._.Harmony_Management API.
 * 
 * This class uses the @OpenAPIDefinition annotation to provide metadata 
 * for the API documentation, including version, title, and description.
 * 
 * @version 1.0.0
 * @title Glow._.Harmony_Management API
 * @description Glow._.Harmony_Management Documentation
 */
@Configuration
@OpenAPIDefinition(
  info = @Info(
    version = "1.0.0",
    title = "Glow._.Harmony_Management API",
    description = "Glow._.Harmony_Management Documentation"
  )
)
public class OpenApiConfig {}
