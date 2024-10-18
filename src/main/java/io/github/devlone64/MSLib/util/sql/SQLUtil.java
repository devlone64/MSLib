package io.github.devlone64.MSLib.util.sql;

import io.github.devlone64.MSLib.builder.database.data.SQLConnection;
import io.github.devlone64.MSLib.builder.database.handler.ClassHandler;
import io.github.devlone64.MSLib.builder.database.handler.PreparedHandler;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUtil {

    @SneakyThrows
    public static PreparedStatement prepareStatement(SQLConnection conn, String sql, Object... objectsToSet) {
        var statement = conn.getConnection().prepareStatement(sql);
        for (int i = 0; i < objectsToSet.length; i++) statement.setObject(i + 1, objectsToSet[i]);
        return statement;
    }

}