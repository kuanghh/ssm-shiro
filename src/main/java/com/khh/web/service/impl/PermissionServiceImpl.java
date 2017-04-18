package com.khh.web.service.impl;

import com.khh.web.dao.PermissionMapper;
import com.khh.web.domain.Permission;
import com.khh.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 * 权限服务层
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private PermissionMapper permissionMapper;

    public int insert(Permission permission) {
        return permissionMapper.insert(permission);
    }

    public int update(Permission permission) {
        return permissionMapper.update(permission);
    }

    public int deleteById(Permission permission) {
        return permissionMapper.deleteById(permission.getId());
    }

    public Permission findById(String id) {
        return permissionMapper.findById(id);
    }

    @Override
    public List<Permission> findAllByRoleId(String id) {
        return permissionMapper.findAllByRoleId(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }
}
