package com.khh.web.service;

import com.khh.web.domain.Role;
import com.khh.web.domain.User;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 */
public interface RoleService {

    int insert(Role role);

    int update(Role role);

    int deleteById(Role role);

    Role findById(String id);

    List<Role> findAllByUserId(String id);
}
