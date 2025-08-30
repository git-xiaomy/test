package com.example.scaffold.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Base Entity with common fields
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity {
    
    /**
     * Primary key ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * Create time
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * Update time
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * Logical delete flag (0: not deleted, 1: deleted)
     */
    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}