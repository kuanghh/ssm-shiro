package com.khh.part3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 * 测试授权
 */
public class TestRole {

    public static void login(String filePath,String name,String password){
        //1获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =new IniSecurityManagerFactory(filePath);
        //2、得到SecurityManager实例并绑定给SecurityUtils
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("登录成功"); //ok
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }
    }

    public static Subject subject(){
        return SecurityUtils.getSubject();
    }

    @Test
    public void testHashRole(){
        login("classpath:shiro-test/shiro-role.ini","zhang","123");
        System.out.println("subject has role1 ? " + subject().hasRole("role1"));
        System.out.println("subject has role1 & role2 ?" + subject().hasAllRoles(Arrays.asList("role1","role2")));
        boolean[] results = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        System.out.println(results[0]);
        System.out.println(results[1]);
        System.out.println(results[2]);
    }

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-test/shiro-permission.ini","zhang","123");

        System.out.println(subject().isPermitted("user:create"));
        subject().checkPermission("user:create");

        System.out.println(subject().isPermittedAll("user:create","user:delete"));
    }

}
