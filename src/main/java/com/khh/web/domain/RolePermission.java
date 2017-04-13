package com.khh.web.domain;

import java.io.Serializable;

public class RolePermission implements Serializable {
    private String id;

    private String roleId;

    private String permissionId;

    private boolean isVaild;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

    public boolean getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(boolean isVaild) {
        this.isVaild = isVaild;
    }
}