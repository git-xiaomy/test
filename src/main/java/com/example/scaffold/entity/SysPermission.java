package com.example.scaffold.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * System Permission Entity
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permission")
public class SysPermission extends BaseEntity {
    
    /**
     * Permission name
     */
    @TableField("permission_name")
    private String permissionName;
    
    /**
     * Permission code
     */
    @TableField("permission_code")
    private String permissionCode;
    
    /**
     * Resource type (menu, button, api)
     */
    @TableField("resource_type")
    private String resourceType;
    
    /**
     * Request URL
     */
    @TableField("url")
    private String url;
    
    /**
     * Request method
     */
    @TableField("method")
    private String method;
    
    /**
     * Parent permission ID
     */
    @TableField("parent_id")
    private Long parentId;
    
    /**
     * Sort order
     */
    @TableField("sort_order")
    private Integer sortOrder;
    
    /**
     * Permission description
     */
    @TableField("description")
    private String description;
    
    /**
     * Status (0: disabled, 1: enabled)
     */
    @TableField("status")
    private Integer status;
}