package com.khh.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class User implements Serializable{
    private String id;

    private String name;

    private String password;

    private int state;

    private Date createTime;

    //默认为有效
    private boolean isValid = true;

    public static int USER_STATE_ENABLE = 1;
    public static int USER_STATE_DISABLE = 0;

    public User(){
        this.id = UUID.randomUUID().toString().replaceAll("-","");
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}