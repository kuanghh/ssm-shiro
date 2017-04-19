package com.khh.web.dao;

import com.khh.core.bean.UserBean;
import com.khh.web.domain.User;

import java.util.List;

public interface UserMapper {

    /**
     * 新增
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 更新
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 身份认证--->根据name和密码认证
     * @param user
     * @return
     */
    User authenticationByName(User user);

    /**
     * 身份认证--->根据email和密码认证
     * @param user
     * @return
     */
    User authenticationByEmail(User user);

    /**
     * 根据名字获取User
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 根据email获取User
     * @param email
     * @return
     */
    User findByUserEmail(String email);

    /**
     * 获取全部用户详细信息
     * @return
     */
    List<UserBean> findAllWithDetails();

    /**
     * 根据id查询用户详细信息
     * @param id
     * @return
     */
    UserBean findByIdWithDetails(String id);
}