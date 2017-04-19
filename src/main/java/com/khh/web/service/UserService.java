package com.khh.web.service;

import com.khh.core.bean.UserBean;
import com.khh.web.domain.User;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
public interface UserService {

    int insert(UserBean userBean);

    int update(UserBean user);

    int deleteById(String user);

    UserBean findById(String id);

    User authenticationByName(User user);

    User authenticationByEmail(User user);

    User findByUserEmail(String email);

    User findByUserName(String username);

    List<UserBean> findAll();
}
