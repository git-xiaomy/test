package com.example.scaffold.utils;

import com.example.scaffold.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security Utility Class
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
public class SecurityUtils {
    
    /**
     * Get current authenticated user
     * 
     * @return CustomUserDetails or null if not authenticated
     */
    public static CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }
        
        return null;
    }
    
    /**
     * Get current user ID
     * 
     * @return current user ID or null if not authenticated
     */
    public static Long getCurrentUserId() {
        CustomUserDetails currentUser = getCurrentUser();
        return currentUser != null ? currentUser.getUserId() : null;
    }
    
    /**
     * Get current username
     * 
     * @return current username or null if not authenticated
     */
    public static String getCurrentUsername() {
        CustomUserDetails currentUser = getCurrentUser();
        return currentUser != null ? currentUser.getUsername() : null;
    }
    
    /**
     * Check if current user has role
     * 
     * @param role role to check
     * @return true if user has the role
     */
    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
        }
        
        return false;
    }
    
    /**
     * Check if current user has permission
     * 
     * @param permission permission to check
     * @return true if user has the permission
     */
    public static boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(permission));
        }
        
        return false;
    }
    
    /**
     * Check if user is authenticated
     * 
     * @return true if user is authenticated
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && 
               !"anonymousUser".equals(authentication.getPrincipal());
    }
}