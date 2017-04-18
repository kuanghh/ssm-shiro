package com.khh.core.common;

/**
 * Created by 951087952@qq.com on 2017/4/18.
 * 匹配正则表达式
 */
public class Matches {
    /**
     * 密码正则表达式
     * 6位数字
     */
    final public static String PASSWORDPATTERN = "^[0-9]{6}$";

    public static void main(String[] args) {
        String str = "1234567";
        boolean matches = str.matches(PASSWORDPATTERN);
        System.out.println(matches);
    }
}
