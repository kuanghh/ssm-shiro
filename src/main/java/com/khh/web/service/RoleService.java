package com.khh.web.service;

import com.khh.core.bean.RoleBean;
import com.khh.web.domain.Role;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 */
public interface RoleService {

    /**
     * 添加新角色
     * @param roleBean
     * @return
     */
    int insert(RoleBean roleBean);

    /**
     * 更新角色
     * @param roleBean
     * @return
     */
    int update(RoleBean roleBean);

    /**
     * 删除角色(假删除)
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 根据Id查询角色详细信息
     * @param id
     * @return
     */
    RoleBean findById(String id);

    /**
     * 通过用户Id查询角色
     * @param id
     * @return
     */
    List<Role> findAllByUserId(String id);

    /**
     * 查询所有角色详细信息
     * @return
     */
    List<RoleBean> findAll();
}
