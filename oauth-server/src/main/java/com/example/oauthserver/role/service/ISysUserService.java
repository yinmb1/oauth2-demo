package com.example.oauthserver.role.service;


import com.example.oauthserver.role.entity.SysUser;

public interface ISysUserService {

    SysUser getByUsername(String username);
}
