package com.example.scaffold.controller;

import com.example.scaffold.vo.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Health Check Controller
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@RestController
@RequestMapping("/")
public class HealthController {
    
    /**
     * Health check endpoint
     * 
     * @return health status
     */
    @GetMapping("/health")
    public ApiResponse<Map<String, Object>> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", LocalDateTime.now());
        health.put("application", "SpringBoot Scaffold");
        health.put("version", "1.0.0");
        
        return ApiResponse.success("Application is running", health);
    }
    
    /**
     * API info endpoint
     * 
     * @return API information
     */
    @GetMapping("/info")
    public ApiResponse<Map<String, Object>> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "SpringBoot Scaffold");
        info.put("description", "A comprehensive Spring Boot scaffold with MyBatis-Plus, Security, and MySQL");
        info.put("version", "1.0.0");
        info.put("author", "scaffold-generator");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Health Check", "GET /health");
        endpoints.put("Login", "POST /auth/login");
        endpoints.put("Logout", "POST /auth/logout");
        endpoints.put("Current User", "GET /auth/me");
        endpoints.put("User Management", "GET /admin/user/page");
        
        info.put("endpoints", endpoints);
        
        return ApiResponse.success("API Information", info);
    }
}