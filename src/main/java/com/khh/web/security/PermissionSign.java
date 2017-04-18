package com.khh.web.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 */
public class PermissionSign {

    private PermissionSign(){}

    /**
     * 用户新增权限 标识
     */
    public static final String USER_CREATE = "user:create";

    /**
     * 用户删除权限 标识
     */
    public static final String USER_DELETE = "user:delete";

    /**
     * 用户修改权限 标识
     */
    public static final String USER_UPDATE = "user:update";

    /**
     * 查看用户权限 标识
     */
    public static final String USER_READ = "user:read";

    /**
     * 角色新增权限 标识
     */
    public static final String ROLE_CREATE = "role:create";

    /**
     * 角色删除权限 标识
     */
    public static final String ROLE_DELETE = "role:delete";

    /**
     * 角色修改权限 标识
     */
    public static final String ROLE_UPDATE = "role:update";

    /**
     * 查看角色权限 标识
     */
    public static final String ROLE_READ = "role:read";

    /**
     * 查看权限  的权限标识
     */
    public static final String PERMISSION_READALL = "permission:readALL";


//    /**
//     * 存放所有权限的map
//     */
//    public static final Map<String,Object> PERMISSSION_MAP = new HashMap<String,Object>();
//
//    static{
//        PERMISSSION_MAP.put("用户新增",USER_CREATE);
//        PERMISSSION_MAP.put("用户删除",USER_DELETE);
//        PERMISSSION_MAP.put("用户修改",USER_UPDATE);
//        PERMISSSION_MAP.put("查看用户",USER_READ);
//        PERMISSSION_MAP.put("角色新增",ROLE_CREATE);
//        PERMISSSION_MAP.put("角色删除",ROLE_DELETE);
//        PERMISSSION_MAP.put("角色修改",ROLE_UPDATE);
//        PERMISSSION_MAP.put("查看角色",ROLE_READ);
//        PERMISSSION_MAP.put("查看权限",PERMISSION_READALL);
//    }

}
