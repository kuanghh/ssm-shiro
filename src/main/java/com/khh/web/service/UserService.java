package com.khh.web.service;

import com.khh.web.domain.User;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
public interface UserService {

    int insert(User user);

    int update(User user);

    int deleteById(User user);

    User findById(String id);

    User authenticationByName(User user);

    User authenticationByEmail(User user);

    User findByUserEmail(String email);

    User findByUserName(String username);
}
