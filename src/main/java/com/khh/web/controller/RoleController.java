package com.khh.web.controller;

import com.khh.core.base.controller.BaseController;
import com.khh.core.bean.ResponseBean;
import com.khh.core.bean.RoleBean;
import com.khh.web.domain.Role;
import com.khh.web.security.PermissionSign;
import com.khh.web.security.RoleSign;
import com.khh.web.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 * 角色控制器
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.ROLE_READ)
    @RequestMapping(value = "/getAllRoles",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getAllRoles() throws Exception{
        ResponseBean responseBean = new ResponseBean();

        List<RoleBean> roleList = roleService.findAll();
        responseBean.setSuccessResponse("获取成功");
        responseBean.setData("roleList", roleList);

        return responseBean;
    }

    /**
     * 获取所有角色标识
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.ROLE_READ)
    @RequestMapping(value = "/getAllRolesSign",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getAllRolesSign() throws Exception{
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccessResponse("获取成功");
        responseBean.setData("roleSignsMap",RoleSign.ROLESMAP);
        return responseBean;
    }

    /**
     * 添加角色
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.ROLE_CREATE)
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addRole(@Valid RoleBean roleBean, BindingResult result) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        if(result.hasErrors()){
            responseBean.setErrorResponse(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        int i = roleService.insert(roleBean);
        if(i == 0){
            responseBean.setErrorResponse("添加失败");
            return  responseBean;
        }
        responseBean.setSuccessResponse("添加成功");
        return responseBean;
    }

    /**
     * 修改角色---回显信息
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.ROLE_UPDATE)
    @RequestMapping(value = "/beforeEditRole",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean beforeEditRole(String id) throws Exception{
        ResponseBean responseBean = new ResponseBean();

        RoleBean roleBean = roleService.findById(id);
        responseBean.setData("role",roleBean);
        responseBean.setSuccessResponse("回显成功");
        return responseBean;
    }

    /**
     * 修改角色
     * @param roleBean
     * @param result
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.ROLE_UPDATE)
    @RequestMapping(value = "/editRole",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean editRole(@Valid RoleBean roleBean, BindingResult result) throws Exception{
        ResponseBean responseBean = new ResponseBean();

        if (result.hasErrors()) {
            responseBean.setErrorResponse(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        int i = roleService.update(roleBean);
        if (i == 0) {
            responseBean.setErrorResponse("修改失败");
            return responseBean;
        }
        responseBean.setSuccessResponse("修改成功");

        return responseBean;
    }

    /**
     * 删除角色
     * @param id
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.ROLE_DELETE)
    @RequestMapping(value = "/deleteRole",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean deleteRole(String id) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        int i = roleService.deleteById(id);
        if(i == 0){
            responseBean.setErrorResponse("删除失败");
        }
        responseBean.setSuccessResponse("删除成功");
        return responseBean;
    }

}
