package com.khh.core.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 951087952@qq.com on 2017/4/19.
 */
public class UserBean implements Serializable{

    private String id;

    @NotNull
    @Length(max = 20 ,message = "用户名长度不能大于20")
    private String name;

    @NotNull
    @Length(min = 6,max = 6 ,message = "密码为6位")
    private String password;

    private int state;

    @NotNull
    @Email
    private String email;

    private Date createTime;

    @Size(min = 1,message = "不能没有角色")
    private String[] roleIds;

    private List<RoleBean> roleBeanList = new ArrayList<RoleBean>();

    public UserBean(){
        this.id = UUID.randomUUID().toString().replaceAll("-","");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public List<RoleBean> getRoleBeanList() {
        return roleBeanList;
    }

    public void setRoleBeanList(List<RoleBean> roleBeanList) {
        this.roleBeanList = roleBeanList;
    }
}
