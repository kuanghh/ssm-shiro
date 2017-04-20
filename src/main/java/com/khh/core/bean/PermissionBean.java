package com.khh.core.bean;

import com.khh.web.util.CodeUtils;

/**
 * Created by 951087952@qq.com on 2017/4/19.
 */
public class PermissionBean {

    private String id;

    private String name;

    private String sign;

    private String description;

    public PermissionBean(){
        this.id = CodeUtils.getUUID();
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
