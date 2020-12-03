package com.example.oauthserver.role.mapper;



import com.example.oauthserver.role.entity.SysRolePermission;

import java.util.List;

public interface SysRolePermissionMapper {

    List<SysRolePermission> findByRoleIds(List<Integer> roleIds);
}
