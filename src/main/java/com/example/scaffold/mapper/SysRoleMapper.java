package com.example.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scaffold.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * System Role Mapper
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    
    /**
     * Find roles by user ID
     * 
     * @param userId user ID
     * @return role list
     */
    List<SysRole> findRolesByUserId(@Param("userId") Long userId);
    
    /**
     * Find roles by user IDs
     * 
     * @param userIds user ID list
     * @return role list
     */
    List<SysRole> findRolesByUserIds(@Param("userIds") List<Long> userIds);
}