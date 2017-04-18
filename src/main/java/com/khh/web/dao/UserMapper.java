package com.khh.web.dao;

import com.khh.web.domain.User;

public interface UserMapper {

    int insert(User user);

    int update(User user);

    int deleteById(String id);

    User findById(String id);


    User authentication(User user);

    User findByUserName(String username);
}