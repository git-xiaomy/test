package com.example.scaffold.controller;

import com.example.scaffold.vo.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * API Documentation Controller
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@RestController
@RequestMapping("/docs")
public class ApiDocController {
    
    /**
     * Get API documentation
     * 
     * @return API documentation
     */
    @GetMapping
    public ApiResponse<Map<String, Object>> getApiDocs() {
        Map<String, Object> docs = new HashMap<>();
        docs.put("title", "Spring Boot Scaffold API");
        docs.put("version", "1.0.0");
        docs.put("description", "A comprehensive Spring Boot scaffold with authentication and user management");
        
        Map<String, Object> endpoints = new HashMap<>();
        
        // Authentication endpoints
        Map<String, String> authEndpoints = new HashMap<>();
        authEndpoints.put("POST /auth/login", "User login - Request: {username, password}");
        authEndpoints.put("POST /auth/logout", "User logout");
        authEndpoints.put("GET /auth/me", "Get current user information");
        endpoints.put("Authentication", authEndpoints);
        
        // User management endpoints
        Map<String, String> userEndpoints = new HashMap<>();
        userEndpoints.put("GET /admin/user/page", "Get user list with pagination - Params: page, size, keyword");
        userEndpoints.put("GET /admin/user/{id}", "Get user by ID");
        userEndpoints.put("POST /admin/user", "Create new user - Request: UserRequestDTO");
        userEndpoints.put("PUT /admin/user/{id}", "Update user - Request: UserRequestDTO");
        userEndpoints.put("DELETE /admin/user/{id}", "Delete user");
        userEndpoints.put("PATCH /admin/user/{id}/status", "Change user status - Param: status");
        userEndpoints.put("PATCH /admin/user/{id}/password", "Reset user password - Param: newPassword");
        endpoints.put("User Management (Admin Only)", userEndpoints);
        
        // Health endpoints
        Map<String, String> healthEndpoints = new HashMap<>();
        healthEndpoints.put("GET /health", "Application health check");
        healthEndpoints.put("GET /info", "Application information");
        endpoints.put("Health & Info", healthEndpoints);
        
        docs.put("endpoints", endpoints);
        
        // Authentication info
        Map<String, Object> auth = new HashMap<>();
        auth.put("type", "JWT Bearer Token");
        auth.put("header", "Authorization: Bearer <token>");
        auth.put("defaultUser", Map.of("username", "admin", "password", "admin123"));
        docs.put("authentication", auth);
        
        // Response format
        Map<String, Object> responseFormat = new HashMap<>();
        responseFormat.put("success", Map.of(
            "code", 200,
            "message", "Success",
            "data", "Response data",
            "timestamp", "Unix timestamp"
        ));
        responseFormat.put("error", Map.of(
            "code", "Error code (400/401/403/500)",
            "message", "Error message",
            "data", null,
            "timestamp", "Unix timestamp"
        ));
        docs.put("responseFormat", responseFormat);
        
        return ApiResponse.success("API Documentation", docs);
    }
}