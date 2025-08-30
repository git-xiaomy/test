package com.example.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scaffold.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * System Permission Mapper
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    
    /**
     * Find permissions by user ID
     * 
     * @param userId user ID
     * @return permission list
     */
    List<SysPermission> findPermissionsByUserId(@Param("userId") Long userId);
    
    /**
     * Find permissions by role ID
     * 
     * @param roleId role ID
     * @return permission list
     */
    List<SysPermission> findPermissionsByRoleId(@Param("roleId") Long roleId);
}