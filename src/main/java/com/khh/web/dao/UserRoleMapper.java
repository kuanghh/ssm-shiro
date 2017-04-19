package com.khh.web.dao;

import com.khh.web.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    /**
     * 增加
     * @param record
     * @return
     */
    int insert(UserRole record);

    /**
     * 批量增加
     * @param userRoleList
     * @return
     */
    int insertAll(@Param(value = "list") List<UserRole> userRoleList);
}