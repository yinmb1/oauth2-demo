package com.example.oauthserver.role.service;



import com.example.oauthserver.role.entity.SysPermission;

import java.util.List;


public interface ISysPermissionService {

    List<SysPermission> findByUserId(Integer userId);
}
