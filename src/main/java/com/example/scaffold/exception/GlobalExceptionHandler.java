package com.example.scaffold.exception;

import com.example.scaffold.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Business exception
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusinessException(BusinessException e) {
        log.error("Business exception: {}", e.getMessage());
        return ApiResponse.error(e.getCode(), e.getMessage());
    }
    
    /**
     * Validation exception
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Map<String, String>> handleValidationException(Exception e) {
        log.error("Validation exception: {}", e.getMessage());
        
        Map<String, String> errors = new HashMap<>();
        
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }
        
        return new ApiResponse<>(400, "Validation failed", errors);
    }
    
    /**
     * Authentication exception
     */
    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<Void> handleAuthenticationException(Exception e) {
        log.error("Authentication exception: {}", e.getMessage());
        return ApiResponse.unauthorized("Authentication failed");
    }
    
    /**
     * Access denied exception
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Access denied exception: {}", e.getMessage());
        return ApiResponse.forbidden("Access denied");
    }
    
    /**
     * Illegal argument exception
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Illegal argument exception: {}", e.getMessage());
        return ApiResponse.badRequest(e.getMessage());
    }
    
    /**
     * Generic exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> handleGenericException(Exception e) {
        log.error("Unexpected exception: ", e);
        return ApiResponse.error("Internal server error");
    }
}