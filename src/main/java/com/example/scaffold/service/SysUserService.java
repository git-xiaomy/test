package com.example.scaffold.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaffold.dto.LoginRequestDTO;
import com.example.scaffold.dto.UserRequestDTO;
import com.example.scaffold.entity.SysUser;
import com.example.scaffold.vo.UserVO;

/**
 * System User Service Interface
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
public interface SysUserService {
    
    /**
     * User login
     * 
     * @param loginRequest login request
     * @return JWT token
     */
    String login(LoginRequestDTO loginRequest);
    
    /**
     * Get user by username
     * 
     * @param username username
     * @return user entity
     */
    SysUser getUserByUsername(String username);
    
    /**
     * Get user page
     * 
     * @param page page number
     * @param size page size
     * @param keyword search keyword
     * @return user page
     */
    Page<UserVO> getUserPage(long page, long size, String keyword);
    
    /**
     * Get user by ID
     * 
     * @param id user ID
     * @return user VO
     */
    UserVO getUserById(Long id);
    
    /**
     * Create user
     * 
     * @param userRequest user request
     * @return created user
     */
    UserVO createUser(UserRequestDTO userRequest);
    
    /**
     * Update user
     * 
     * @param userRequest user request
     * @return updated user
     */
    UserVO updateUser(UserRequestDTO userRequest);
    
    /**
     * Delete user
     * 
     * @param id user ID
     * @return success flag
     */
    boolean deleteUser(Long id);
    
    /**
     * Change user status
     * 
     * @param id user ID
     * @param status new status
     * @return success flag
     */
    boolean changeUserStatus(Long id, Integer status);
    
    /**
     * Reset user password
     * 
     * @param id user ID
     * @param newPassword new password
     * @return success flag
     */
    boolean resetPassword(Long id, String newPassword);
}