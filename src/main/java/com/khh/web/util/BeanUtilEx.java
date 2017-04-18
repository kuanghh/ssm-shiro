package com.khh.web.util;

import org.apache.commons.beanutils.ConvertUtils;

/**
 * Created by 951087952@qq.com on 2017/3/31 0031.
 *
 */
public class BeanUtilEx extends org.apache.commons.beanutils.BeanUtils {


    private BeanUtilEx() {
    }

    static {
// 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlTimestampConverter(null), java.sql.Timestamp.class);
// 注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空
    }

    public static void copyProperties(Object target, Object source)  {
        if(target == null && source == null){
            return ;
        }
// 支持对日期copy
        try{
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static Object copyProperties2(Object target, Object source)  {
        if(target == null && source == null){
            return null;
        }
// 支持对日期copy
        try{
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
            return target;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
