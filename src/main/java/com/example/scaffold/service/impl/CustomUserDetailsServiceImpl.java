package com.example.scaffold.service.impl;

import com.example.scaffold.entity.SysPermission;
import com.example.scaffold.entity.SysRole;
import com.example.scaffold.entity.SysUser;
import com.example.scaffold.mapper.SysPermissionMapper;
import com.example.scaffold.mapper.SysRoleMapper;
import com.example.scaffold.mapper.SysUserMapper;
import com.example.scaffold.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Custom UserDetailsService Implementation
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private SysUserMapper userMapper;
    
    @Autowired
    private SysRoleMapper roleMapper;
    
    @Autowired
    private SysPermissionMapper permissionMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        // Get user roles
        List<SysRole> roles = roleMapper.findRolesByUserId(user.getId());
        
        // Get user permissions
        List<SysPermission> permissions = permissionMapper.findPermissionsByUserId(user.getId());
        
        return new CustomUserDetails(user, roles, permissions);
    }
}