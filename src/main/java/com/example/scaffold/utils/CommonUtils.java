package com.example.scaffold.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Common Utility Class
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
public class CommonUtils {
    
    /**
     * Generate UUID without hyphens
     * 
     * @return UUID string
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * Generate short UUID (8 characters)
     * 
     * @return short UUID string
     */
    public static String generateShortUUID() {
        return generateUUID().substring(0, 8);
    }
    
    /**
     * MD5 hash
     * 
     * @param input input string
     * @return MD5 hash
     */
    public static String md5(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Current timestamp string
     * 
     * @return formatted timestamp
     */
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Current date string
     * 
     * @return formatted date
     */
    public static String getCurrentDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
    /**
     * Check if string is empty or null
     * 
     * @param str input string
     * @return true if empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Check if string is not empty and not null
     * 
     * @param str input string
     * @return true if not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * Mask sensitive information (keep first and last 2 characters)
     * 
     * @param input input string
     * @param maskChar mask character
     * @return masked string
     */
    public static String maskSensitiveInfo(String input, char maskChar) {
        if (isEmpty(input) || input.length() <= 4) {
            return input;
        }
        
        int length = input.length();
        StringBuilder masked = new StringBuilder();
        masked.append(input.substring(0, 2));
        
        for (int i = 2; i < length - 2; i++) {
            masked.append(maskChar);
        }
        
        masked.append(input.substring(length - 2));
        return masked.toString();
    }
    
    /**
     * Mask phone number
     * 
     * @param phone phone number
     * @return masked phone number
     */
    public static String maskPhone(String phone) {
        return maskSensitiveInfo(phone, '*');
    }
    
    /**
     * Mask email address
     * 
     * @param email email address
     * @return masked email
     */
    public static String maskEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }
        
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return email;
        }
        
        return maskSensitiveInfo(parts[0], '*') + "@" + parts[1];
    }
}