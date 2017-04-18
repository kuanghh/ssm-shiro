package com.khh.web.service.impl;

import com.khh.web.dao.RoleMapper;
import com.khh.web.domain.Role;
import com.khh.web.domain.User;
import com.khh.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public int insert(Role role) {
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
}
