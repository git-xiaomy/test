package com.example.scaffold.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Role Response VO
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class RoleVO {
    
    /**
     * Role ID
     */
    private Long id;
    
    /**
     * Role name
     */
    private String roleName;
    
    /**
     * Role code
     */
    private String roleCode;
    
    /**
     * Role description
     */
    private String description;
    
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
    
    public String getStatusText() {
        return status == 1 ? "Enabled" : "Disabled";
    }
}