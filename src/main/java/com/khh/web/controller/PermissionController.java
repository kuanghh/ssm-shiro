package com.khh.web.controller;

import com.khh.core.base.controller.BaseController;
import com.khh.core.bean.ResponseBean;
import com.khh.core.bean.RoleBean;
import com.khh.web.domain.Permission;
import com.khh.web.domain.Role;
import com.khh.web.security.PermissionSign;
import com.khh.web.service.PermissionService;
import com.khh.web.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 * 角色控制器
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @Resource
    private PermissionService permissionService;

    /**
     * 获取所有权限
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.PERMISSION_READALL)
    @RequestMapping(value = "/getAllPermission",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getAllRoles() throws Exception{
        ResponseBean responseBean = new ResponseBean();
        List<Permission> permissionList = permissionService.findAll();
        responseBean.setData("permissionList",permissionList);
        responseBean.setSuccessResponse("获取所有权限成功");
        return responseBean;
    }

}
