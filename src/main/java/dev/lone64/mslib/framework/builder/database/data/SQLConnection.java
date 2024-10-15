package dev.lone64.mslib.framework.builder.database.data;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLConnection {
    Connection getConnection() throws SQLException;
}