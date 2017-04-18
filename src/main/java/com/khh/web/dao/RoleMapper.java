package com.khh.web.dao;

import com.khh.web.domain.Role;

import java.util.List;

public interface RoleMapper {

    int insert(Role role);

    int update(Role role);

    int deleteById(String id);

    Role findById(String id);


    List<Role> findAllByUserId(String id);

    List<Role> findAll();
}