package com.khh.core.bean;

import com.khh.core.common.Matches;

import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 * 用户登录的Bean
 */
public class LoginUserBean implements Serializable{


    private String name;

    private String email;

    //密码
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = Matches.PASSWORDPATTERN,message = "密码格式错误")
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
