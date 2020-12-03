package com.example.oauthserver.role.service.impl;

import com.example.oauthserver.role.entity.SysUser;
import com.example.oauthserver.role.mapper.SysUserMapper;
import com.example.oauthserver.role.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.findByUserName(username);
    }
}
