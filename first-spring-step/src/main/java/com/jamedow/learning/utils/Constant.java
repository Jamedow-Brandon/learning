package com.jamedow.learning.utils;

import com.jamedow.learning.entity.Users;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yoyo on 2017/2/15.
 */
public class Constant {

    public static final int ZERO = 0;

    public static final String Y = "Y";

    public static final String N = "N";



    /**
     * @param src 源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    public static String change(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }

    /**
     * 遍历对象的属性和值
     */
    private void getAllParams() {
        try {
            Users users = new Users();
            Field[] fields = users.getClass().getDeclaredFields();
            for (Field field : fields) {
                String param = field.getName();
                Method m = users.getClass().getMethod("get" + change(field.getName()));
                String value = (String) m.invoke(users);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
