package com.khh.web.controller;

import com.khh.core.bean.ResponseBean;
import com.khh.web.domain.User;
import com.khh.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/4/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

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
