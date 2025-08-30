package com.example.scaffold.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User Response VO
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class UserVO {
    
    /**
     * User ID
     */
    private Long id;
    
    /**
     * Username
     */
    private String username;
    
    /**
     * Nickname
     */
    private String nickname;
    
    /**
     * Email
     */
    private String email;
    
    /**
     * Phone number
     */
    private String phone;
    
    /**
     * Avatar URL
     */
    private String avatar;
    
    /**
     * Status (0: disabled, 1: enabled)
     */
    private Integer status;
    
    /**
     * Status text
     */
    private String statusText;
    
    /**
     * Create time
     */
    private LocalDateTime createTime;
    
    /**
     * Update time
     */
    private LocalDateTime updateTime;
    
    /**
     * Last login time
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * User roles
     */
    private List<RoleVO> roles;
    
    public String getStatusText() {
        return status == 1 ? "Enabled" : "Disabled";
    }
}