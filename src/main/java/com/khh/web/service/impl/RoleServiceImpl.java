package com.khh.web.service.impl;

import com.khh.core.bean.PermissionBean;
import com.khh.core.bean.RoleBean;
import com.khh.web.dao.PermissionMapper;
import com.khh.web.dao.RoleMapper;
import com.khh.web.dao.RolePermissionMapper;
import com.khh.web.domain.Permission;
import com.khh.web.domain.Role;
import com.khh.web.domain.RolePermission;
import com.khh.web.service.RoleService;
import com.khh.web.util.BeanUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<RolePermission> rpList = new ArrayList<>();
        for (int i = 0; i < permissionId.length; i++) {
            RolePermission rp = new RolePermission();
            rp.setPermissionId(permissionId[i]);
            rp.setRoleId(role.getId());
            rpList.add(rp);
        }
        rolePermissionMapper.insertAll(rpList);

        return roleMapper.insert(role);
    }

    public int update(RoleBean roleBean) {
        //bean 转 domain
        Role role = (Role) BeanUtilEx.copyProperties2(new Role(),roleBean);
        //修改之后的权限
        String[] permissionId = roleBean.getPermissionId();
        //原本存在关系的权限
        List<RolePermission> rplist = rolePermissionMapper.findByRoleId(role.getId());
        List<String> pIdsFromDB = rplist.stream().map(rp -> rp.getPermissionId()).collect(Collectors.toList());

        //需要新增的关系
        List<RolePermission> newRP = new ArrayList<>();
        //需要更改的关系
        List<RolePermission> updateRP = new ArrayList<>();

        /**
         * 有四种情况
         *  1.提交的角色权限关系，原本数据库不存在，此时要添加
         *  2.提交的角色权限关系，原本数据库存在并无效，此时要更改有效
         *  3.提交的角色权限关系，原本数据库存在且有效，此时也更改(以后处理是否有更好的解决办法)
         *  4.没有提交的角色权限关系，原本数据库也存在，并有效，此时要更改为无效
         */
        for (int i = 0; i < permissionId.length; i++) {
            if(pIdsFromDB.contains(permissionId[i])){
                RolePermission rp = new RolePermission(role.getId(),permissionId[i]);
                updateRP.add(rp);
                rolePermissionMapper.update(rp,1);
            }else{
                RolePermission rp = new RolePermission(role.getId(),permissionId[i]);
                newRP.add(rp);
            }
        }
        rplist.removeAll(updateRP);
        for (int i = 0; i < rplist.size(); i++) {
            RolePermission rp = rplist.get(i);
            rp.setIsValid(false);
            rolePermissionMapper.update(rp,0);
        }

        if(newRP.size() > 0){
            rolePermissionMapper.insertAll(newRP);
        }
        return roleMapper.update(role);
    }

    public int deleteById(String id) {
        Role role = roleMapper.findById(id);
        if(role == null){
            return 0;
        }
        role.setIsValid(false);
        return roleMapper.update(role);
    }

    public RoleBean findById(String id) {
        return roleMapper.findDetailById(id);
    }

    @Override
    public List<Role> findAllByUserId(String id) {
        return roleMapper.findAllByUserId(id);
    }

    @Override
    public List<RoleBean> findAll() {
        return roleMapper.findAllWithDetail();
    }
}
