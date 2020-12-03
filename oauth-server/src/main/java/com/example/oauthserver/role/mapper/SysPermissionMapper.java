package com.example.oauthserver.role.mapper;




import com.example.oauthserver.role.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper  {


    List<SysPermission> findByIds(List<Integer> ids);

}
