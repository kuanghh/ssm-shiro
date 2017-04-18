package com.khh.web.security;

import com.khh.web.domain.Permission;
import com.khh.web.domain.Role;
import com.khh.web.domain.User;
import com.khh.web.service.PermissionService;
import com.khh.web.service.RoleService;
import com.khh.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 951087952@qq.com on 2017/4/17.
 * 认证管理器
 */
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public String getName() {
        return "securityRealm";
    }

    //支持什么类型的token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 权限检查
     * @param principal
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principal.getPrimaryPrincipal();
        final User user = userService.findByUserName(username);
        //找到所有角色
        List<Role> list = roleService.findAllByUserId(user.getId());
        Stream<String> rSignstream = list.stream().map(r -> r.getSign());
        authorizationInfo.addRoles(rSignstream.collect(Collectors.toList()));
        //找到所有权限
        Stream<String> pSignstream = list.stream().flatMap(r -> {
            List<Permission> pl = permissionService.findAllByRoleId(r.getId());
            return pl.stream().map(p -> p.getSign());
        });
        authorizationInfo.addStringPermissions(pSignstream.collect(Collectors.toList()));
        return authorizationInfo;
    }

    /**
     * 登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        //通过数据库进行验证
        final User user = userService.authentication(new User(username,password));
        if(user == null){
            throw new AuthenticationException("用户名或密码错误");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        return authenticationInfo;
    }
}
