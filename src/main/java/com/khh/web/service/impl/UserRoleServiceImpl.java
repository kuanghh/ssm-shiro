package com.khh.web.service.impl;

import com.khh.web.dao.UserRoleMapper;
import com.khh.web.domain.UserRole;
import com.khh.web.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 *
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }
}
