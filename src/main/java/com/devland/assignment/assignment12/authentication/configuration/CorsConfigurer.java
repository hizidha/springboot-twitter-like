package com.devland.assignment.assignment12.authentication.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**").allowedOrigins("*");
    }
}