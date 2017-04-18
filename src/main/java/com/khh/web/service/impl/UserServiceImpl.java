package com.khh.web.service.impl;

import com.khh.web.dao.UserMapper;
import com.khh.web.domain.User;
import com.khh.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public int deleteById(User user) {
        return userMapper.deleteById(user.getId());
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
    public User authenticationByEmail(User user) {
        return userMapper.authenticationByEmail(user);
    }

    @Override
    public User findByUserEmail(String email) {
        return userMapper.findByUserEmail(email);
    }
}
