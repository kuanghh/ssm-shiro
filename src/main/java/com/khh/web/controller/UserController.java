package com.khh.web.controller;

import com.khh.core.bean.ResponseBean;
import com.khh.core.common.Const;
import com.khh.web.domain.User;
import com.khh.web.security.PermissionSign;
import com.khh.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /***
     * 登录
     * @param user
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean login(User user, HttpSession session) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            responseBean.setErrorResponse("已经登录了");
            return responseBean;
        }
        //身份验证
        try{
            subject.login(new UsernamePasswordToken(user.getName(),user.getPassword()));
        }catch (AuthenticationException e){
            responseBean.setErrorResponse("登录失败");
            return responseBean;
        }
        User u = userService.findByUserName(user.getName());
        //验证成功在Session中保存用户信息
        session.setAttribute(Const.LOGIN_USER,u);
        responseBean.setSuccessResponse("登录成功");
        return responseBean;
    }

    /**
     * 注销
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exit" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean exit(HttpSession session) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            responseBean.setErrorResponse("请登录");
            return responseBean;
        }
        try{
            //退出
            subject.logout();
            //↑退出这里，已经是调用了session.removeAttribute(Const.LOGIN_USER);
            //session.removeAttribute(Const.LOGIN_USER);
        }catch (AuthenticationException e){
            responseBean.setErrorResponse("注销失败");
            return responseBean;
        }
        responseBean.setSuccessResponse("注销成功");
        return responseBean;
    }

    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean addUser(User user) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        user.setState(User.USER_STATE_DISABLE);
        user.setCreateTime(new Date());
        int i = userService.insert(user);
        if(i == 0){
            responseBean.setErrorResponse("添加失败");
            return responseBean;
        }
        responseBean.setSuccessResponse("添加成功");
        return responseBean;
    }

}
