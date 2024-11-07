package com.devland.assignment.assignment12.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Twitter-like Service", version = "1.0.0"))
//@SecurityScheme(
//        name = "bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer"
//)
public class OpenApiConfiguration { }