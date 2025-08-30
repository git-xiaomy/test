package com.example.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scaffold.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * System User Mapper
 * 
 * @author scaffold-generator
 * @since 1.0.0
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    
    /**
     * Find user by username
     * 
     * @param username username
     * @return user
     */
    @Select("SELECT * FROM sys_user WHERE username = #{username} AND deleted = 0")
    SysUser findByUsername(@Param("username") String username);
    
    /**
     * Find users with roles by user IDs
     * 
     * @param userIds user ID list
     * @return users with roles
     */
    List<SysUser> findUsersWithRoles(@Param("userIds") List<Long> userIds);
    
    /**
     * Update user last login time
     * 
     * @param userId user ID
     * @return affected rows
     */
    @Select("UPDATE sys_user SET last_login_time = NOW() WHERE id = #{userId}")
    int updateLastLoginTime(@Param("userId") Long userId);
}