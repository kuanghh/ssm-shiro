package com.khh.web.service;

import com.khh.web.domain.Permission;
import com.khh.web.domain.Role;
import com.khh.web.domain.User;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 */
public interface PermissionService {

    int insert(Permission permission);

    Permission findById(String id);

    List<Permission> findAllByRoleId(String id);

    List<Permission> findAll();
}
