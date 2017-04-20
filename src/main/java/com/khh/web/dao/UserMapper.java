package com.khh.web.dao;

import com.khh.core.bean.UserBean;
import com.khh.web.domain.User;
import org.apache.ibatis.annotations.Param;

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
     * 根据id查询(查询有效或者无效)
     * @param id
     * @return
     */
    User findById(@Param(value = "id")String id,@Param(value = "isValid") boolean isValid);

    /**
     * 身份认证--->根据name和密码认证(只查询有效数据)
     * @param user
     * @return
     */
    User authenticationByName(User user);

    /**
     * 身份认证--->根据email和密码认证(只查询有效数据)
     * @param user
     * @return
     */
    User authenticationByEmail(User user);

    /**
     * 根据名字获取User(只查询有效数据)
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 根据email获取User(只查询有效数据)
     * @param email
     * @return
     */
    User findByUserEmail(String email);

    /**
     * 获取全部用户详细信息(只查询有效数据)
     * @return
     */
    List<UserBean> findAllWithDetails();

    /**
     * 根据id查询用户详细信息(只查询有效数据)
     * @param id
     * @return
     */
    UserBean findByIdWithDetails(String id);
}