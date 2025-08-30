package com.example.scaffold.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Common API Response wrapper
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class ApiResponse<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Response code
     */
    private Integer code;
    
    /**
     * Response message
     */
    private String message;
    
    /**
     * Response data
     */
    private T data;
    
    /**
     * Timestamp
     */
    private Long timestamp;
    
    public ApiResponse() {
        this.timestamp = System.currentTimeMillis();
    }
    
    public ApiResponse(Integer code, String message) {
        this();
        this.code = code;
        this.message = message;
    }
    
    public ApiResponse(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }
    
    /**
     * Success response
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "Success");
    }
    
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }
    
    /**
     * Error response
     */
    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(500, "Internal Server Error");
    }
    
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(500, message);
    }
    
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message);
    }
    
    /**
     * Bad request
     */
    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(400, message);
    }
    
    /**
     * Unauthorized
     */
    public static <T> ApiResponse<T> unauthorized() {
        return new ApiResponse<>(401, "Unauthorized");
    }
    
    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(401, message);
    }
    
    /**
     * Forbidden
     */
    public static <T> ApiResponse<T> forbidden() {
        return new ApiResponse<>(403, "Forbidden");
    }
    
    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(403, message);
    }
    
    /**
     * Set data and return this
     */
    public ApiResponse<T> data(T data) {
        this.data = data;
        return this;
    }
}