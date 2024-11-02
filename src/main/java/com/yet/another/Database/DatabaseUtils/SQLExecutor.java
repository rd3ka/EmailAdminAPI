package com.yet.another.Database.DatabaseUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SQLExecutor<T> {
    T execute(PreparedStatement statement) throws SQLException;
}
