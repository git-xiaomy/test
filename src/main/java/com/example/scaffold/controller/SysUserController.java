package com.example.scaffold.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaffold.dto.UserRequestDTO;
import com.example.scaffold.service.SysUserService;
import com.example.scaffold.vo.ApiResponse;
import com.example.scaffold.vo.PageVO;
import com.example.scaffold.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * System User Controller
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
@PreAuthorize("hasRole('ADMIN')")
public class SysUserController {
    
    @Autowired
    private SysUserService userService;
    
    /**
     * Get user page
     * 
     * @param page page number
     * @param size page size
     * @param keyword search keyword
     * @return user page data
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('user:list')")
    public ApiResponse<PageVO<UserVO>> getUserPage(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "10") Long size,
            @RequestParam(required = false) String keyword) {
        try {
            Page<UserVO> userPage = userService.getUserPage(page, size, keyword);
            PageVO<UserVO> pageVO = new PageVO<>(
                userPage.getCurrent(),
                userPage.getSize(),
                userPage.getTotal(),
                userPage.getRecords()
            );
            return ApiResponse.success(pageVO);
        } catch (Exception e) {
            log.error("Get user page failed: {}", e.getMessage());
            return ApiResponse.error("Get user page failed: " + e.getMessage());
        }
    }
    
    /**
     * Get user by ID
     * 
     * @param id user ID
     * @return user info
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:list')")
    public ApiResponse<UserVO> getUserById(@PathVariable Long id) {
        try {
            UserVO user = userService.getUserById(id);
            return ApiResponse.success(user);
        } catch (Exception e) {
            log.error("Get user by ID failed: {}", e.getMessage());
            return ApiResponse.error("Get user failed: " + e.getMessage());
        }
    }
    
    /**
     * Create user
     * 
     * @param userRequest user request
     * @return created user
     */
    @PostMapping
    @PreAuthorize("hasAuthority('user:create')")
    public ApiResponse<UserVO> createUser(@RequestBody @Validated UserRequestDTO userRequest) {
        try {
            UserVO user = userService.createUser(userRequest);
            return ApiResponse.success("User created successfully", user);
        } catch (Exception e) {
            log.error("Create user failed: {}", e.getMessage());
            return ApiResponse.error("Create user failed: " + e.getMessage());
        }
    }
    
    /**
     * Update user
     * 
     * @param id user ID
     * @param userRequest user request
     * @return updated user
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public ApiResponse<UserVO> updateUser(@PathVariable Long id, @RequestBody @Validated UserRequestDTO userRequest) {
        try {
            userRequest.setId(id);
            UserVO user = userService.updateUser(userRequest);
            return ApiResponse.success("User updated successfully", user);
        } catch (Exception e) {
            log.error("Update user failed: {}", e.getMessage());
            return ApiResponse.error("Update user failed: " + e.getMessage());
        }
    }
    
    /**
     * Delete user
     * 
     * @param id user ID
     * @return success response
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        try {
            boolean success = userService.deleteUser(id);
            if (success) {
                return ApiResponse.success("User deleted successfully");
            } else {
                return ApiResponse.error("Delete user failed");
            }
        } catch (Exception e) {
            log.error("Delete user failed: {}", e.getMessage());
            return ApiResponse.error("Delete user failed: " + e.getMessage());
        }
    }
    
    /**
     * Change user status
     * 
     * @param id user ID
     * @param status new status
     * @return success response
     */
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAuthority('user:update')")
    public ApiResponse<String> changeUserStatus(@PathVariable Long id, @RequestParam Integer status) {
        try {
            boolean success = userService.changeUserStatus(id, status);
            if (success) {
                return ApiResponse.success("User status changed successfully");
            } else {
                return ApiResponse.error("Change user status failed");
            }
        } catch (Exception e) {
            log.error("Change user status failed: {}", e.getMessage());
            return ApiResponse.error("Change user status failed: " + e.getMessage());
        }
    }
    
    /**
     * Reset user password
     * 
     * @param id user ID
     * @param newPassword new password (optional)
     * @return success response
     */
    @PatchMapping("/{id}/password")
    @PreAuthorize("hasAuthority('user:update')")
    public ApiResponse<String> resetPassword(@PathVariable Long id, @RequestParam(required = false) String newPassword) {
        try {
            boolean success = userService.resetPassword(id, newPassword);
            if (success) {
                return ApiResponse.success("Password reset successfully");
            } else {
                return ApiResponse.error("Reset password failed");
            }
        } catch (Exception e) {
            log.error("Reset password failed: {}", e.getMessage());
            return ApiResponse.error("Reset password failed: " + e.getMessage());
        }
    }
}