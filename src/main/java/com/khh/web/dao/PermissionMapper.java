package com.khh.web.dao;

import com.khh.web.domain.Permission;

import java.util.List;

public interface PermissionMapper {

    int insert(Permission permission);

    int update(Permission permission);

    Permission findById(String id);

    List<Permission> findAllByRoleId(String id);

    List<Permission> findAll();
}