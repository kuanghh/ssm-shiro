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

    /**
     * 根据id查询
     * @param id
     * @return
     */
    List<UserRole> findByUserId(@Param(value = "user_id") String id);

    /**
     * 更新（有效或者无效）
     * @param ur
     * @param i
     */
    void update(@Param(value = "ur") UserRole ur,@Param(value = "isValid") int i);

    /**
     * 批量更改为有效或无效
     * @param urs
     * @param isValid
     */
    void updateAll(@Param(value = "list")List<UserRole> urs,@Param(value = "isValid")boolean isValid);



}