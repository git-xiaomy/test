package com.example.scaffold.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * System Role Entity
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {
    
    /**
     * Role name
     */
    @TableField("role_name")
    private String roleName;
    
    /**
     * Role code
     */
    @TableField("role_code")
    private String roleCode;
    
    /**
     * Role description
     */
    @TableField("description")
    private String description;
    
    /**
     * Status (0: disabled, 1: enabled)
     */
    @TableField("status")
    private Integer status;
}