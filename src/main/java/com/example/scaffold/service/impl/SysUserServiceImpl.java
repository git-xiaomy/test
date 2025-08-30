package com.example.scaffold.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaffold.dto.LoginRequestDTO;
import com.example.scaffold.dto.UserRequestDTO;
import com.example.scaffold.entity.SysRole;
import com.example.scaffold.entity.SysUser;
import com.example.scaffold.exception.BusinessException;
import com.example.scaffold.mapper.SysRoleMapper;
import com.example.scaffold.mapper.SysUserMapper;
import com.example.scaffold.service.SysUserService;
import com.example.scaffold.utils.JwtUtils;
import com.example.scaffold.vo.RoleVO;
import com.example.scaffold.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * System User Service Implementation
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Slf4j
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    
    @Autowired
    private SysUserMapper userMapper;
    
    @Autowired
    private SysRoleMapper roleMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Override
    public String login(LoginRequestDTO loginRequest) {
        SysUser user = getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new BusinessException("User not found");
        }
        
        if (user.getStatus() == 0) {
            throw new BusinessException("User is disabled");
        }
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid username or password");
        }
        
        // Update last login time
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(user);
        
        // Generate JWT token
        return jwtUtils.generateToken(user.getUsername(), user.getId());
    }
    
    @Override
    public SysUser getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    
    @Override
    public Page<UserVO> getUserPage(long page, long size, String keyword) {
        Page<SysUser> userPage = new Page<>(page, size);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        
        if (StringUtils.hasText(keyword)) {
            queryWrapper.and(wrapper -> wrapper
                .like("username", keyword)
                .or()
                .like("nickname", keyword)
                .or()
                .like("email", keyword)
            );
        }
        
        queryWrapper.orderByDesc("create_time");
        Page<SysUser> result = userMapper.selectPage(userPage, queryWrapper);
        
        // Convert to VO
        Page<UserVO> voPage = new Page<>();
        BeanUtils.copyProperties(result, voPage);
        
        List<UserVO> userVOs = result.getRecords().stream()
            .map(this::convertToUserVO)
            .collect(Collectors.toList());
        
        voPage.setRecords(userVOs);
        return voPage;
    }
    
    @Override
    public UserVO getUserById(Long id) {
        SysUser user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("User not found");
        }
        return convertToUserVO(user);
    }
    
    @Override
    public UserVO createUser(UserRequestDTO userRequest) {
        // Check if username exists
        if (getUserByUsername(userRequest.getUsername()) != null) {
            throw new BusinessException("Username already exists");
        }
        
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userRequest, user);
        
        // Encode password
        if (StringUtils.hasText(userRequest.getPassword())) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode("123456")); // Default password
        }
        
        user.setStatus(userRequest.getStatus() != null ? userRequest.getStatus() : 1);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        
        userMapper.insert(user);
        
        // Handle roles if provided
        if (userRequest.getRoleIds() != null && userRequest.getRoleIds().length > 0) {
            // TODO: Handle user role relationships
        }
        
        return convertToUserVO(user);
    }
    
    @Override
    public UserVO updateUser(UserRequestDTO userRequest) {
        if (userRequest.getId() == null) {
            throw new BusinessException("User ID is required");
        }
        
        SysUser existingUser = userMapper.selectById(userRequest.getId());
        if (existingUser == null) {
            throw new BusinessException("User not found");
        }
        
        // Check if username is changed and exists
        if (!existingUser.getUsername().equals(userRequest.getUsername())) {
            if (getUserByUsername(userRequest.getUsername()) != null) {
                throw new BusinessException("Username already exists");
            }
        }
        
        BeanUtils.copyProperties(userRequest, existingUser);
        existingUser.setUpdateTime(LocalDateTime.now());
        
        // Update password if provided
        if (StringUtils.hasText(userRequest.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        
        userMapper.updateById(existingUser);
        
        return convertToUserVO(existingUser);
    }
    
    @Override
    public boolean deleteUser(Long id) {
        if (id == null) {
            throw new BusinessException("User ID is required");
        }
        
        SysUser user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("User not found");
        }
        
        return userMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean changeUserStatus(Long id, Integer status) {
        if (id == null) {
            throw new BusinessException("User ID is required");
        }
        
        SysUser user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("User not found");
        }
        
        user.setStatus(status);
        user.setUpdateTime(LocalDateTime.now());
        
        return userMapper.updateById(user) > 0;
    }
    
    @Override
    public boolean resetPassword(Long id, String newPassword) {
        if (id == null) {
            throw new BusinessException("User ID is required");
        }
        
        SysUser user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("User not found");
        }
        
        String encodedPassword = StringUtils.hasText(newPassword) ? 
            passwordEncoder.encode(newPassword) : 
            passwordEncoder.encode("123456");
        
        user.setPassword(encodedPassword);
        user.setUpdateTime(LocalDateTime.now());
        
        return userMapper.updateById(user) > 0;
    }
    
    /**
     * Convert SysUser to UserVO
     * 
     * @param user SysUser entity
     * @return UserVO
     */
    private UserVO convertToUserVO(SysUser user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        
        // Get user roles
        List<SysRole> roles = roleMapper.findRolesByUserId(user.getId());
        List<RoleVO> roleVOs = roles.stream()
            .map(role -> {
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(role, roleVO);
                return roleVO;
            })
            .collect(Collectors.toList());
        
        userVO.setRoles(roleVOs);
        return userVO;
    }
}