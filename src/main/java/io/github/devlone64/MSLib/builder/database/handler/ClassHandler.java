package io.github.devlone64.MSLib.builder.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ClassHandler<T> {
    T consume(ResultSet e) throws SQLException;
}