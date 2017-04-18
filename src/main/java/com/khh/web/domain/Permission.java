package com.khh.web.domain;

import java.io.Serializable;

public class Permission implements Serializable {
    private String id;

    private String name;

    private String sign;

    private String description;

    private boolean idValid;

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

    public boolean getIdValid() {
        return idValid;
    }

    public void setIdValid(boolean idValid) {
        this.idValid = idValid;
    }
}