package com.example.oauthserver.role.mapper;

import com.example.oauthserver.role.entity.SysUserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SysUserRoleMapper {

    @Select("select * from sys_user_role where user_id=#{userId}")
    List<SysUserRole> findByUserId(Integer userId);
}
