package com.example.scaffold.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * User Login Request DTO
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class LoginRequestDTO {
    
    /**
     * Username
     */
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 64, message = "Username length must be between 3-64 characters")
    private String username;
    
    /**
     * Password
     */
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 32, message = "Password length must be between 6-32 characters")
    private String password;
    
    /**
     * Verification code (optional)
     */
    private String captcha;
}