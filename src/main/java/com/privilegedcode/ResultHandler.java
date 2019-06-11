package com.privilegedcode;

import java.sql.ResultSet;

/**
 * Created by jin on 2019-06-09.
 */
public interface ResultHandler<T> {

    T handle(ResultSet resultSet);
}
