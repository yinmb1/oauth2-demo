package com.example.oauthserver.role.mapper;


import com.example.oauthserver.role.entity.SysUser;

public interface SysUserMapper {

    SysUser findByUserName(String userName);
}
