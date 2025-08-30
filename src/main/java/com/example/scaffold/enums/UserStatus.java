package com.example.scaffold.enums;

/**
 * User Status Enum
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
public enum UserStatus {
    DISABLED(0, "Disabled"),
    ENABLED(1, "Enabled");
    
    private final Integer code;
    private final String description;
    
    UserStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static UserStatus fromCode(Integer code) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown user status code: " + code);
    }
}