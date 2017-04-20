package com.khh.web.util;

import java.util.UUID;

/**
 * Created by 951087952@qq.com on 2017/4/20.
 */
public class CodeUtils {
    private CodeUtils(){

    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println(CodeUtils.getUUID());
        }
    }

}
