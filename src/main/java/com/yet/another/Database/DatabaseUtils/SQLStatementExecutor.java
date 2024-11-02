package com.yet.another.Database.DatabaseUtils;

import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface SQLStatementExecutor<T> {
    T execute(Statement statement) throws SQLException;

}
