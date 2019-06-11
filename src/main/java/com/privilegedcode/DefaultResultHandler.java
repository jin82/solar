package com.privilegedcode;

import com.privilegedcode.reflect.ObjectPropertyParser;
import lombok.NonNull;

import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jin on 2019-06-09.
 */
public class DefaultResultHandler<T> implements ResultHandler<T>{

    private Class<T> type;

    private ObjectPropertyParser<T> parser;

    private static Map<String, DefaultResultHandler> handlers = new ConcurrentHashMap<>();

    public static DefaultResultHandler getInstance(Class<?> clazz) {
        return handlers.computeIfAbsent(clazz.getName(),k -> new DefaultResultHandler<>(clazz));
    }

    private DefaultResultHandler(@NonNull Class<T> type) {
        this.type = type;
        this.parser = new ObjectPropertyParser<>(type);
    }

    @Override
    public T handle(ResultSet resultSet) {
        //TODO SQL API IMPLEMENTS
//        resultSet.
        return null;
    }
}
