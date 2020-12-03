package com.example.oauthserver.role.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -71361364183138269L;

    private Integer id;

    private String roleName;

    private String roleCode;

    private String roleDescription;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
