package com.khh.core.bean;

import com.khh.web.domain.Permission;
import com.khh.web.util.CodeUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 */
public class RoleBean implements Serializable{


    private String id;

    @NotNull(message = "角色名不能为空")
    @Length(max = 20 ,message = "角色名长度不能大于20")
    private String name;

    @Length(max = 30 ,message = "描述长度不能大于30")
    private String description;

    @NotNull(message = "角色标识不能为空")
    private String sign;

    @Size(min = 1,max = 9,message = "不能没有权限")
    private String[] permissionId;

    private List<PermissionBean> permissionList = new ArrayList<>();

    public RoleBean(){ this.id = CodeUtils.getUUID();}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String[] getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String[] permissionId) {
        this.permissionId = permissionId;
    }

    public List<PermissionBean> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionBean> permissionList) {
        this.permissionList = permissionList;
    }
}
