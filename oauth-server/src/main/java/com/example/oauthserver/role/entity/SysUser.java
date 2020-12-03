package com.example.oauthserver.role.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private Integer status = 0;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
