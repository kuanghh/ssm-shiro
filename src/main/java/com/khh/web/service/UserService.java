package com.khh.web.service;

import com.khh.core.bean.UserBean;
import com.khh.web.domain.User;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
public interface UserService {

    /**
     * 增加用户
     * @param userBean
     * @return
     */
    int insert(UserBean userBean);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int update(UserBean user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int deleteById(String user);

    /**
     * 根据id查询用户详细信息
     * @param id
     * @return
     */
    UserBean findById(String id);


    /**
     * 用户通过名字认证(包含密码)
     * @param user
     * @return
     */
    User authenticationByName(User user);

    /**
     * 用户通过邮件认证(包含密码)
     * @param user
     * @return
     */
    User authenticationByEmail(User user);

    /**
     * 根据用户email查询用户
     * @param email
     * @return
     */
    User findByUserEmail(String email);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 查询所有用户详细信息
     * @return
     */
    List<UserBean> findAll();
}
