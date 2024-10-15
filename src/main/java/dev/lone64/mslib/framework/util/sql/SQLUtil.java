package dev.lone64.mslib.framework.util.sql;

import dev.lone64.mslib.framework.builder.database.data.SQLConnection;
import dev.lone64.mslib.framework.builder.database.handler.ClassHandler;
import dev.lone64.mslib.framework.builder.database.handler.PreparedHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUtil {

    public static PreparedStatement prepared(SQLConnection con, String sql, Object... objects) throws SQLException {
        try (Connection conn = con.getConnection(); PreparedStatement prepared = conn.prepareStatement(sql)) {
            if (objects != null && objects.length > 0) {
                for (int i = 0; i < objects.length; i++) {
                    prepared.setObject(i + 1, objects[i]);
                }
            }
            return prepared;
        }
    }

    public static int executeUpdate(SQLConnection con, String sql) {
        return executeUpdate(con, sql, prepared -> { });
    }

    public static int executeUpdate(SQLConnection con, String sql, PreparedHandler handler) {
        try (PreparedStatement prepared = prepared(con, sql)) {
            handler.consume(prepared);
            return prepared.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    public static <T> T executeQuery(SQLConnection con, String sql, ClassHandler<T> handler, Object... objects) {
        try (PreparedStatement prepared = prepared(con, sql, objects)) {
            try (ResultSet result = prepared.executeQuery()) {
                return result.next() ? handler.consume(result) : null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public static <T> List<T> executeQueryList(SQLConnection con, String sql, ClassHandler<T> handler, Object... objects) {
        try (PreparedStatement prepared = prepared(con, sql, objects)) {
            try (ResultSet res = prepared.executeQuery()) {
                List<T> arraylist = new ArrayList<>();
                while (res.next()) {
                    arraylist.add(handler.consume(res));
                }
                return arraylist;
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

}