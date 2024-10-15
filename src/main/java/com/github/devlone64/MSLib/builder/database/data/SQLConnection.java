package com.github.devlone64.MSLib.builder.database.data;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLConnection {
    Connection getConnection() throws SQLException;
}