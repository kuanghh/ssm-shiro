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
    private UserMapper mapper;

    public int insert(User user) {
        return mapper.insert(user);
    }
}
