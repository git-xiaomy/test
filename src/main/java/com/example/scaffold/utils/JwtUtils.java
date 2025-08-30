package com.example.scaffold.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT Utility Class
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Slf4j
@Component
public class JwtUtils {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    /**
     * Generate JWT token
     * 
     * @param username username
     * @param userId user ID
     * @return JWT token
     */
    public String generateToken(String username, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("userId", userId);
        return generateToken(claims, username);
    }
    
    /**
     * Generate JWT token with claims
     * 
     * @param claims claims
     * @param subject subject
     * @return JWT token
     */
    private String generateToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expiration);
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    
    /**
     * Get username from token
     * 
     * @param token JWT token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    /**
     * Get user ID from token
     * 
     * @param token JWT token
     * @return user ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return Long.valueOf(claims.get("userId").toString());
    }
    
    /**
     * Get expiration date from token
     * 
     * @param token JWT token
     * @return expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    /**
     * Get claim from token
     * 
     * @param token JWT token
     * @param claimsResolver claims resolver
     * @param <T> type
     * @return claim value
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * Get all claims from token
     * 
     * @param token JWT token
     * @return claims
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    /**
     * Check if token is expired
     * 
     * @param token JWT token
     * @return true if expired
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    /**
     * Validate token
     * 
     * @param token JWT token
     * @param username username
     * @return true if valid
     */
    public Boolean validateToken(String token, String username) {
        try {
            final String usernameFromToken = getUsernameFromToken(token);
            return (usernameFromToken.equals(username) && !isTokenExpired(token));
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * Get signing key
     * 
     * @return SecretKey
     */
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}