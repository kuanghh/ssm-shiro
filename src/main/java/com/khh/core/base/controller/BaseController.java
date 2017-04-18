package com.khh.core.base.controller;

import com.khh.core.bean.ResponseBean;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 * BaseController  用来处理异常
 */
public class BaseController {
    /**
     * 使用@ExceptionHandler注解，继承此类的Controller发生异常时会自动执行该方法
     * @param e
     * @return
     */
    @ExceptionHandler
    public String exception(Exception e) {

        //对异常进行判断做相应的处理
        if(e instanceof AuthorizationException){
            System.out.println("go to index.jsp");
            return "forward:/index.jsp";
        }
        return null;
    }
}
