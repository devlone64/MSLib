package dev.lone64.mslib.framework.builder.database.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedHandler {
    void consume(PreparedStatement e) throws SQLException;
}