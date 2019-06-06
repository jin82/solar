package com.privilegedcode.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by jin on 2019-06-06.
 */
public class ObjectProperty{
    private String name;
    private Field field;
    private Method setter;
    private Method getter;

    public ObjectProperty(Field field, Method setter, Method getter) {
        this.field = field;
        this.name = field.getName();
        this.setter = setter;
        this.getter = getter;
    }

    public boolean hasGetter() {
        return getter != null;
    }

    public boolean hasSetter() {
        return setter != null;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }
}
