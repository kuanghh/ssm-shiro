package com.khh.web.service.impl;

import com.khh.core.bean.RoleBean;
import com.khh.web.dao.RoleMapper;
import com.khh.web.dao.RolePermissionMapper;
import com.khh.web.domain.Role;
import com.khh.web.domain.RolePermission;
import com.khh.web.domain.User;
import com.khh.web.service.RolePermissionService;
import com.khh.web.service.RoleService;
import com.khh.web.util.BeanUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 * 角色服务层
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RoleMapper roleMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public int insert(RoleBean roleBean) {
        //获取权限id
        String[] permissionId = roleBean.getPermissionId();
        //TODO 验证权限id的合法性(包括了id是否为空，id的长度，id是否真实存在于数据库)  看否能用缓存解决
        //bean转domain
        Role role = (Role) BeanUtilEx.copyProperties2(new Role(), roleBean);

        List<String> pIdList = Arrays.asList(permissionId);
        for (int i = 0; i < pIdList.size(); i++) {
            RolePermission rp = new RolePermission();
            rp.setPermissionId(pIdList.get(i));
            rp.setRoleId(role.getId());
            rolePermissionMapper.insert(rp);
        }
        return roleMapper.insert(role);
    }

    public int update(Role role) {
        return roleMapper.update(role);
    }

    public int deleteById(Role role) {
        return roleMapper.deleteById(role.getId());
    }

    public Role findById(String id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> findAllByUserId(String id) {
        return roleMapper.findAllByUserId(id);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
