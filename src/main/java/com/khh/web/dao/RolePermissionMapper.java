package com.khh.web.dao;

import com.khh.web.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    /**
     * 增加
     * @param record
     * @return
     */
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
    List<RolePermission> findByRoleId(@Param(value = "roleId")String id);

    /**
     * 更改为有效或者无效
     * @param
     */
    void update(@Param(value = "rp")RolePermission rp,@Param(value = "isValid") int isValid);

    /**
     * 批量更改为有效或无效
     * @param rps
     * @param isValid
     */
    void updateAll(@Param(value = "list")List<RolePermission> rps,@Param(value = "isValid")boolean isValid);


}