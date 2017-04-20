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

import java.util.*;
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
        List<String> pIds_New = new ArrayList<>(Arrays.asList(permissionId));
        //原本存在关系的权限
        List<RolePermission> rplist = rolePermissionMapper.findByRoleId(role.getId());
        //键值类型 ： permissionId : Id
        Map<String, String> map = rplist.stream().collect(Collectors.toMap(RolePermission::getId, RolePermission::getPermissionId));

        //需要新增的关系
        List<RolePermission> newRP = new ArrayList<>();
        //需要更改为有效的关系
        List<RolePermission> updateRPToValid = new ArrayList<>();
        //需要更改为无效的关系
        List<RolePermission> updateRPToNotValid = new ArrayList<>();
        /**
         * 有四种情况
         *  1.提交的角色权限关系，原本数据库不存在，此时要添加
         *  2.提交的角色权限关系，原本数据库存在并无效，此时要更改有效
         *  3.提交的角色权限关系，原本数据库存在且有效，此时也更改(以后处理是否有更好的解决办法)
         *  4.没有提交的角色权限关系，原本数据库也存在，并有效，此时要更改为无效
         */
        for (Map.Entry<String, String> entries : map.entrySet()){
            if(pIds_New.contains(entries.getValue())){
                updateRPToValid.add(new RolePermission(entries.getKey(),role.getId(),entries.getValue()));
            }else{
                RolePermission rp = new RolePermission(entries.getKey(),role.getId(),entries.getValue());
                rp.setIsValid(false);
                updateRPToNotValid.add(rp);
            }
        }

        pIds_New.removeAll(updateRPToValid.stream().map(RolePermission::getPermissionId).collect(Collectors.toList()));
        pIds_New.removeAll(updateRPToNotValid.stream().map(RolePermission::getPermissionId).collect(Collectors.toList()));
        for (int i = 0; i < pIds_New.size(); i++) {
            newRP.add(new RolePermission(role.getId(),pIds_New.get(i)));
        }

        //插入新的关系
        if(newRP.size() > 0){
            rolePermissionMapper.insertAll(newRP);
        }
        //更改原本无效的记录为有效
        if(updateRPToValid.size() > 0){
            rolePermissionMapper.updateAll(updateRPToValid,true);
        }
        //更改原本有效的记录为无效
        if(updateRPToNotValid.size() > 0){
            rolePermissionMapper.updateAll(updateRPToNotValid,false);
        }
        return roleMapper.update(role);
    }

    public int deleteById(String id) {
        Role role = roleMapper.findById(id,true);
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
