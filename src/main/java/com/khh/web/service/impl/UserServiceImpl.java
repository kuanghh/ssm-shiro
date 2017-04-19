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
        List<UserRole> urList = new ArrayList<>();
        //添加客户-角色
        //TODO 验证角色id的合法性(包括了id是否为空，id的长度，id是否真实存在于数据库)  看否能用缓存解决
        String[] roleIds = userBean.getRoleIds();
        for (int i = 0; i < roleIds.length; i++) {
            UserRole ur = new UserRole(user.getId(),roleIds[i]);
            urList.add(ur);
        }
        userRoleMapper.insertAll(urList);
        return userMapper.insert(user);
    }

    public int update(User user) {
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

    public User findById(String id) {
        return userMapper.findById(id);
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
