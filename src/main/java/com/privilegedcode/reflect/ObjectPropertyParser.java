package com.privilegedcode.reflect;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jin on 2019-06-06.
 */
@RequiredArgsConstructor
@Slf4j
public class ObjectPropertyParser<T> {

    private boolean isInit = false;

    @NonNull
    private final Class<T> objectClass;

    private Map<String, ObjectProperty> propertyMap;




    public ObjectProperty getProperty(String name) {
        init();
        return propertyMap.get(name);
    }

    public int putProperty(T t,String name, Object value) {
        init();
        ObjectProperty objectProperty = propertyMap.get(name);
        if (objectProperty == null) {
            return 0;
        }

        return objectProperty.setValue(t, value);
    }

    private void init() {
        if (!isInit) {
            synchronized (objectClass) {
                if (!isInit) {
                    parse();
                    isInit = true;
                }
            }
        }

    }

    private void parse() {
        Field[] declaredFields = objectClass.getDeclaredFields();
        if (declaredFields.length == 0) {
            return;
        }
        propertyMap = Arrays.stream(declaredFields).parallel()
                .map(this::parseSingleProperty)
                .collect(Collectors.toMap(ObjectProperty::getName, p -> p));

    }

    private ObjectProperty parseSingleProperty(Field field) {
        Method setter, getter;
        String setterName = ObjectPropertyUtils.setterName(field);
        try {
            setter = objectClass.getMethod(setterName, field.getType());
        } catch (NoSuchMethodException e) {
            if (log.isDebugEnabled()) {
                log.debug("property {} setter not found", setterName);
            }
            setter = null;
        }

        String getterName = ObjectPropertyUtils.getterName(field);
        try {
            getter = objectClass.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            if (log.isDebugEnabled()) {
                log.debug("property {} getter not found", getterName);
            }
            getter = null;
        }
        return new ObjectProperty(field, setter, getter);
    }


}
