package com.example.scaffold.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * User Create/Update Request DTO
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class UserRequestDTO {
    
    /**
     * User ID (for update)
     */
    private Long id;
    
    /**
     * Username
     */
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 64, message = "Username length must be between 3-64 characters")
    private String username;
    
    /**
     * Password
     */
    @Size(min = 6, max = 32, message = "Password length must be between 6-32 characters")
    private String password;
    
    /**
     * Nickname
     */
    @Size(max = 64, message = "Nickname length cannot exceed 64 characters")
    private String nickname;
    
    /**
     * Email
     */
    @Email(message = "Invalid email format")
    @Size(max = 128, message = "Email length cannot exceed 128 characters")
    private String email;
    
    /**
     * Phone number
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Invalid phone number format")
    private String phone;
    
    /**
     * Avatar URL
     */
    @Size(max = 255, message = "Avatar URL length cannot exceed 255 characters")
    private String avatar;
    
    /**
     * Status (0: disabled, 1: enabled)
     */
    private Integer status;
    
    /**
     * Role IDs
     */
    private Long[] roleIds;
}