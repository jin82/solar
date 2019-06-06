package com.privilegedcode.reflect;

import java.lang.reflect.Field;

/**
 * Created by jin on 2019-06-06.
 */
public class ObjectPropertyUtils {

    public static String getterName(Field field) {
        String name = field.getName();
        return mixUpName("get", name);

    }

    public static String setterName(Field field) {
        String name = field.getName();
        return mixUpName("set", name);
    }

    private static String mixUpName(String first,String second) {
        StringBuilder sb = new StringBuilder(first);
        for (int i = 0; i < second.toCharArray().length; i++) {
            char c = second.charAt(i);
            if (i == 0) {
                sb.append((String.valueOf(c)).toUpperCase());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
