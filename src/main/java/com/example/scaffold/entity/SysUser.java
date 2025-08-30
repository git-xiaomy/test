package com.example.scaffold.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * System User Entity
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {
    
    /**
     * Username
     */
    @TableField("username")
    private String username;
    
    /**
     * Password (encrypted)
     */
    @TableField("password")
    private String password;
    
    /**
     * Nickname
     */
    @TableField("nickname")
    private String nickname;
    
    /**
     * Email
     */
    @TableField("email")
    private String email;
    
    /**
     * Phone number
     */
    @TableField("phone")
    private String phone;
    
    /**
     * Avatar URL
     */
    @TableField("avatar")
    private String avatar;
    
    /**
     * Status (0: disabled, 1: enabled)
     */
    @TableField("status")
    private Integer status;
    
    /**
     * Last login time
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;
}