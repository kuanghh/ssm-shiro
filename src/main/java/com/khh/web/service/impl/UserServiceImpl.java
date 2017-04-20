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

import java.util.*;
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
        List<String> rIds_New =  new ArrayList<>(Arrays.asList(roleIds));
        //数据库存在的用户-角色关系
        List<UserRole> urList = userRoleMapper.findByUserId(user.getId());
        Map<String, String> map = urList.stream().collect(Collectors.toMap(UserRole::getId, UserRole::getRoleId));

        //需要新增的关系
        List<UserRole> newUR = new ArrayList<>();
        //需要更改为有效的关系
        List<UserRole> updateURToValid = new ArrayList<>();
        //需要更改为无效的关系
        List<UserRole> updateURToNotValid = new ArrayList<>();

        for (Map.Entry<String, String> entries : map.entrySet()){
            if(rIds_New.contains(entries.getValue())){
                updateURToValid.add(new UserRole(entries.getKey(),user.getId(),entries.getValue()));
            }else{
                UserRole rp = new UserRole(entries.getKey(),user.getId(),entries.getValue());
                rp.setIsValid(false);
                updateURToNotValid.add(rp);
            }
        }
        rIds_New.removeAll(updateURToValid.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
        rIds_New.removeAll(updateURToNotValid.stream().map(UserRole::getRoleId).collect(Collectors.toList()));

        if(newUR.size() > 0){
            userRoleMapper.insertAll(newUR);
        }
        //更改原本无效的记录为有效
        if(updateURToValid.size() > 0){
            userRoleMapper.updateAll(updateURToValid,true);
        }
        //更改原本有效的记录为无效
        if(updateURToValid.size() > 0){
            userRoleMapper.updateAll(updateURToValid,false);
        }
        return userMapper.update(user);
    }

    public int deleteById(String id) {
        User user = userMapper.findById(id,true);
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
