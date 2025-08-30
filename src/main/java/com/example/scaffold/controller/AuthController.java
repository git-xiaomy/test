package com.example.scaffold.controller;

import com.example.scaffold.dto.LoginRequestDTO;
import com.example.scaffold.service.SysUserService;
import com.example.scaffold.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Authentication Controller
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private SysUserService userService;
    
    /**
     * User login
     * 
     * @param loginRequest login request
     * @return JWT token
     */
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody @Validated LoginRequestDTO loginRequest) {
        try {
            String token = userService.login(loginRequest);
            
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("type", "Bearer");
            
            return ApiResponse.success("Login successful", result);
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return ApiResponse.error("Login failed: " + e.getMessage());
        }
    }
    
    /**
     * User logout
     * 
     * @return success response
     */
    @PostMapping("/logout")
    public ApiResponse<String> logout() {
        // JWT is stateless, so logout is just a client-side operation
        return ApiResponse.success("Logout successful");
    }
    
    /**
     * Get current user info
     * 
     * @return current user info
     */
    @GetMapping("/me")
    public ApiResponse<Map<String, Object>> getCurrentUser() {
        // This would typically get the current user from SecurityContext
        // For now, return a placeholder
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("message", "This endpoint should return current user info");
        
        return ApiResponse.success(userInfo);
    }
}