package com.khh.web.domain;

import com.khh.web.util.CodeUtils;

import java.io.Serializable;
import java.util.UUID;

public class Permission implements Serializable {
    private String id;

    private String name;

    private String sign;

    private String description;

    private boolean isValid = true;

    public Permission(){
        this.id =  this.id = CodeUtils.getUUID();
    }
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIdValid(boolean isValid) {
        this.isValid = isValid;
    }
}