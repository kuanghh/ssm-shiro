package com.khh.web.controller;

import com.khh.core.base.controller.BaseController;
import com.khh.core.bean.ResponseBean;
import com.khh.core.bean.UserBean;
import com.khh.web.domain.User;
import com.khh.web.security.PermissionSign;
import com.khh.web.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;


/**
 * Created by 951087952@qq.com on 2017/4/18.
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;


    /**
     * 添加用户(这里需要有添加用户的权限[create:user])
     * @param userBean
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addUser(@Valid UserBean userBean, BindingResult result) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        if(result.hasErrors()){
            responseBean.setErrorResponse(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        System.out.println("添加用户");
        //TODO  对名字,email 检验不能重复
        int i = userService.insert(userBean);
        if(i == 0){
            responseBean.setErrorResponse("添加失败");
            return responseBean;
        }
        responseBean.setSuccessResponse("添加成功");
        return responseBean;
    }

    /**
     * 用户删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.USER_DELETE)
    @RequestMapping(value = "/deleteUser" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean deleteUser(String id) throws Exception{
        ResponseBean responseBean = new ResponseBean();

        int i = userService.deleteById(id);
        if(i == 0){
            responseBean.setErrorResponse("删除失败");
            return responseBean;
        }
        responseBean.setSuccessResponse("删除成功");
        return responseBean;
    }

    /**
     * 获取所有用户明细
     * @return
     * @throws Exception
     */
    @RequiresPermissions(value = PermissionSign.USER_READ)
    @RequestMapping(value = "/findAll" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean findAll() throws Exception{
        ResponseBean responseBean = new ResponseBean();

        List<UserBean> userBeanList = userService.findAll();
        responseBean.setData("userList",userBeanList);
        responseBean.setSuccessResponse("删除成功");
        return responseBean;
    }



}
