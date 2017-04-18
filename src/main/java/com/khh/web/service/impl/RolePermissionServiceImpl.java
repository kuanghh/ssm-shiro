package com.khh.web.service.impl;

import com.khh.web.dao.RolePermissionMapper;
import com.khh.web.domain.RolePermission;
import com.khh.web.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 *
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int insert(RolePermission rolePermission) {
        return rolePermissionMapper.insert(rolePermission);
    }
}
