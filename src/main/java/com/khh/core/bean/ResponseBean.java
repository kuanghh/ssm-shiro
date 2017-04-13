package com.khh.core.bean;



import com.khh.core.common.ResponseBeanCode;
import com.khh.core.common.ResponseBeanType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求响应Bean
 * Created by Marco on 2016/8/28.
 */
public class ResponseBean implements Serializable {

    private String type ;

    private int code ;

    private String message ;

    private Map<String,Object> data = new HashMap<String, Object>();

    public void setSuccessResponse(String message){
        this.type = ResponseBeanType.SUCCESS ;
        this.code = ResponseBeanCode.SUCCESS ;
        this.message = message ;
    }

    public void setErrorResponse(String message){
        this.type = ResponseBeanType.ERROR ;
        this.code = ResponseBeanCode.ERROR ;
        this.message = message ;
    }

    public void setData(String key,Object obj){
        this.data.put(key,obj);
    }

    public Object getData(String key){
        return this.data.get(key);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
