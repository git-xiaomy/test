package com.example.scaffold.security;

import com.example.scaffold.entity.SysPermission;
import com.example.scaffold.entity.SysRole;
import com.example.scaffold.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom User Details
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Data
public class CustomUserDetails implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    private SysUser user;
    private List<SysRole> roles;
    private List<SysPermission> permissions;
    
    public CustomUserDetails(SysUser user, List<SysRole> roles, List<SysPermission> permissions) {
        this.user = user;
        this.roles = roles;
        this.permissions = permissions;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()))
                .collect(Collectors.toList());
        
        List<GrantedAuthority> permissionAuthorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionCode()))
                .collect(Collectors.toList());
        
        authorities.addAll(permissionAuthorities);
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }
    
    public Long getUserId() {
        return user.getId();
    }
    
    public String getNickname() {
        return user.getNickname();
    }
    
    public String getEmail() {
        return user.getEmail();
    }
}