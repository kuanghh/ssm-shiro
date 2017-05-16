package com.khh.web.controller;

import com.khh.core.base.controller.BaseController;
import com.khh.core.bean.LoginUserBean;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 * 登录控制器
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    @Resource
    private UserService userService;

    /***
     * 登录
     * @param loginUserBean
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean login(@Valid LoginUserBean loginUserBean, BindingResult result, HttpSession session) throws Exception{
        ResponseBean responseBean = new ResponseBean();
        Subject subject = SecurityUtils.getSubject();
        //不能同时登录两个账号
        if(subject.isAuthenticated()){
            responseBean.setErrorResponse("已经登录了");
            return responseBean;
        }
        //信息验证
        if(result.hasErrors()){
            responseBean.setErrorResponse(result.getFieldError().getDefaultMessage());
            return responseBean;
        }
        User u = null;
        //身份验证
        try{
            if(loginUserBean.getName() == null && loginUserBean.getEmail() == null){
                responseBean.setErrorResponse("请填写登录信息");
                return responseBean;
            }
            //如果名字不为空
            if(loginUserBean.getName() != null){
                subject.login(new UsernamePasswordToken(loginUserBean.getName(),loginUserBean.getPassword()));

                u = userService.findByUserName(loginUserBean.getName());
            }
            //如果邮箱不为空
            if(loginUserBean.getEmail() != null){
                subject.login(new UsernamePasswordToken(loginUserBean.getEmail(),loginUserBean.getPassword()));
                u = userService.findByUserEmail(loginUserBean.getEmail());
            }
        }catch (AuthenticationException e){
            e.printStackTrace();
            responseBean.setErrorResponse("登录失败");
            return responseBean;
        }
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
            //退出  同等于session.removeAttribute(Const.LOGIN_USER);
            subject.logout();
        }catch (AuthenticationException e){
            e.printStackTrace();
            responseBean.setErrorResponse("注销失败");
            return responseBean;
        }
        responseBean.setSuccessResponse("注销成功");
        return responseBean;
    }
}
