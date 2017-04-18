package com.khh.web.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 */
public class RoleSign {

    private RoleSign(){}

    /**
     * 普通后台管理员 标识
     */
    public static final String ADMIN = "admin";

    /**
     * 超级后台管理员 标识
     */
    public static final String SYSTEMADMIN = "sys_admin";

    /**
     * 普通用户 标识
     */
    public static final String SIMPLEUSER = "simple_user";

    /**
     * vip用户 标识
     */
    public static final String VIPUSER = "vip_user";

    public static Map<String,Object> ROLESMAP = new HashMap<String,Object>();

    static{
        ROLESMAP.put("普通管理员",ADMIN);
        ROLESMAP.put("超级后台管理员",SYSTEMADMIN);
        ROLESMAP.put("普通用户",SIMPLEUSER);
        ROLESMAP.put("vip用户",VIPUSER);
    }

}
