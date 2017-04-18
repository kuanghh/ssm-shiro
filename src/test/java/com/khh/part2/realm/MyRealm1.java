package com.khh.part2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 * 测试 Realm
 */
public class MyRealm1 implements Realm{

    public String getName() {
        return "myRealm1";
    }

    public boolean supports(AuthenticationToken token) {
        //仅支持UserNamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); // 得到密码
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();//如果用户名错误
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();//如果密码错误
        }
        //如果身份验证成功，返回一个Authenticationinfo实现
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
