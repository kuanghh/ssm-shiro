package com.khh.web.service.impl;

import com.khh.core.bean.UserBean;
import com.khh.web.dao.UserMapper;
import com.khh.web.dao.UserRoleMapper;
import com.khh.web.domain.User;
import com.khh.web.domain.UserRole;
import com.khh.web.service.UserService;
import com.khh.web.util.BeanUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRoleMapper userRoleMapper;

    public int insert(UserBean userBean) {
        //bean 转 domain
        User user = (User) BeanUtilEx.copyProperties2(new User(),userBean);
        user.setCreateTime(new Date());
        //默认有效
        user.setState(User.USER_STATE_ENABLE);

        //开始添加客户-角色
        List<UserRole> urList = new ArrayList<>();
        //TODO 验证角色id的合法性(包括了id是否为空，id的长度，id是否真实存在于数据库)  看否能用缓存解决
        String[] roleIds = userBean.getRoleIds();
        for (int i = 0; i < roleIds.length; i++) {
            UserRole ur = new UserRole(user.getId(),roleIds[i]);
            urList.add(ur);
        }
        userRoleMapper.insertAll(urList);
        return userMapper.insert(user);
    }

    public int update(UserBean userBean) {
        //bean 转 domain
        User user = (User) BeanUtilEx.copyProperties2(new User(),userBean);
        //修改之后的角色
        String[] roleIds = userBean.getRoleIds();
        //数据库存在的用户-角色关系
        List<UserRole> urList = userRoleMapper.findByUserId(user.getId());
        List<String> rIdsFromDB = urList.stream().map(ur -> ur.getRoleId()).collect(Collectors.toList());

        //需要新增的关系
        List<UserRole> newUR = new ArrayList<>();
        //需要更改的关系
        List<UserRole> updateUR = new ArrayList<>();

        for (int i = 0; i < roleIds.length; i++) {
            if(rIdsFromDB.contains(roleIds[i])){
                UserRole ur = new UserRole(user.getId(), roleIds[i]);
                updateUR.add(ur);
                userRoleMapper.update(ur,1);
            }else{
                newUR.add(new UserRole(user.getId(), roleIds[i]));
            }
        }
        urList.removeAll(updateUR);
        for (int i = 0; i < urList.size(); i++) {
            UserRole rp = urList.get(i);
            rp.setIsValid(false);
            userRoleMapper.update(rp,0);
        }
        if(newUR.size() > 0){
            userRoleMapper.insertAll(newUR);
        }
        return userMapper.update(user);
    }

    public int deleteById(String id) {
        User user = userMapper.findById(id);
        if(user == null){
            return 0;
        }
        user.setIsValid(false);
        return userMapper.update(user);
    }

    public UserBean findById(String id) {

        return userMapper.findByIdWithDetails(id);
    }

    @Override
    public User authenticationByName(User user) {
        return userMapper.authenticationByName(user);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public List<UserBean> findAll() {
        return userMapper.findAllWithDetails();
    }

    @Override
    public User authenticationByEmail(User user) {
        return userMapper.authenticationByEmail(user);
    }

    @Override
    public User findByUserEmail(String email) {
        return userMapper.findByUserEmail(email);
    }
}
