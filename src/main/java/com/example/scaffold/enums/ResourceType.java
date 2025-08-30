package com.example.scaffold.enums;

/**
 * Resource Type Enum
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
public enum ResourceType {
    MENU("menu", "Menu"),
    BUTTON("button", "Button"),
    API("api", "API");
    
    private final String code;
    private final String description;
    
    ResourceType(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static ResourceType fromCode(String code) {
        for (ResourceType type : ResourceType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown resource type code: " + code);
    }
}