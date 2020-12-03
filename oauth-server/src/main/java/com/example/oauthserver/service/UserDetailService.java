package com.example.oauthserver.service;

import com.alibaba.fastjson.JSON;
import com.example.oauthserver.role.domin.AuthUser;
import com.example.oauthserver.role.entity.SysPermission;
import com.example.oauthserver.role.entity.SysUser;
import com.example.oauthserver.role.mapper.SysUserMapper;
import com.example.oauthserver.role.service.ISysPermissionService;
import com.example.oauthserver.role.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类都是基于内存的 ，后期会改变为db,需要去数据库中查询
 */

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;





    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysPermissionService sysPermissionService;

    /**
     * 简单的测试demo（可使用）
     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("当前登陆用户名为:{}",username);
//
//        return User.builder().username(username)
//                                     .password(passwordEncoder.encode("123456"))
//                             .authorities("ROLE_ADMIN")
//                             .build();
//    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

            SysUser sysUser = sysUserService.getByUsername(userName);

        if(null == sysUser) {
            log.warn("根据用户名:{}查询用户信息为空",userName);
            throw new UsernameNotFoundException(userName);
        }

        List<SysPermission> sysPermissionList = sysPermissionService.findByUserId(sysUser.getId());

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysPermissionList)) {
            for (SysPermission sysPermission : sysPermissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getUri()));
            }
        }

        AuthUser authUser = new AuthUser(sysUser.getUsername(),passwordEncoder.encode(sysUser.getPassword()),authorityList);
        log.info("用户登陆成功:{}", JSON.toJSONString(authUser));
        return authUser;
    }

}
