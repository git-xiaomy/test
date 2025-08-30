package com.example.scaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Main Application
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.example.scaffold.mapper")
public class ScaffoldApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
        System.out.println("===========================================");
        System.out.println("Spring Boot Scaffold Started Successfully!");
        System.out.println("Access URL: http://localhost:8080/api");
        System.out.println("API Documentation: http://localhost:8080/api/doc.html");
        System.out.println("===========================================");
    }
}