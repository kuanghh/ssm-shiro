package com.khh.web.dao;

import com.khh.web.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    int insert(RolePermission record);

    /**
     * 批量增加
     * @param list
     * @return
     */
    int insertAll(@Param(value = "list") List<RolePermission> list);

    /**
     * 根据角色查找
     * @return
     */
    List<RolePermission> findByRoleId(String id);

    /**
     * 批量更改为有效或者无效
     * @param
     */
    void update(@Param(value = "rp")RolePermission rp,@Param(value = "isValid") int isValid);
}