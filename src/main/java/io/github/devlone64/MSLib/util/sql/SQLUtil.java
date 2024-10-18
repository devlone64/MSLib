package io.github.devlone64.MSLib.util.sql;

import io.github.devlone64.MSLib.builder.database.sql.SQLConnection;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;

public class SQLUtil {

    @SneakyThrows
    public static PreparedStatement prepareStatement(SQLConnection conn, String sql, Object... objectsToSet) {
        var statement = conn.getConnection().prepareStatement(sql);
        for (int i = 0; i < objectsToSet.length; i++) statement.setObject(i + 1, objectsToSet[i]);
        return statement;
    }

}