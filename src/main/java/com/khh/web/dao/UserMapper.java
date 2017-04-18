package com.khh.web.dao;

import com.khh.web.domain.User;

public interface UserMapper {

    int insert(User user);

    int update(User user);

    int deleteById(String id);

    User findById(String id);

    User authenticationByName(User user);

    User authenticationByEmail(User user);

    User findByUserName(String username);

    User findByUserEmail(String email);
}