package com.khh.web.domain;

import java.io.Serializable;

public class Role implements Serializable {
    private String id;

    private String name;

    private String description;

    private String sign;

    private boolean isVaild;

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

    public boolean getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(boolean isVaild) {
        this.isVaild = isVaild;
    }
}