package dev.lone64.mslib.framework.builder.database.impl.database;

import dev.lone64.mslib.framework.builder.database.data.SQLDatabase;
import dev.lone64.mslib.framework.builder.database.handler.ClassHandler;
import dev.lone64.mslib.framework.builder.database.impl.connection.MySQLConnection;
import lombok.Getter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static dev.lone64.mslib.framework.util.sql.SQLUtil.*;

@Getter
public class MySQLDatabase implements SQLDatabase {

    private final MySQLConnection connection;

    public MySQLDatabase(String hostname, String port, String username, String password, String database) {
        this.connection = new MySQLConnection(hostname, port, username, password, database);
    }

    @Override
    public boolean createTable(String table, String columns) {
        return executeUpdate(getConnection(), "CREATE TABLE IF NOT EXISTS %s(%s)".formatted(table, columns)) != -1;
    }

    @Override
    public boolean deleteTable(String table) {
        return executeUpdate(getConnection(), "DROP TABLE IF EXISTS %s".formatted(table)) != -1;
    }

    @Override
    public boolean set(String table, String selected, Object[] values, String logic, String column, String data) {
        if (is(table, column, data)) {
            return executeUpdate(getConnection(), "UPDATE %s SET %s WHERE %s %s ?".formatted(table, "`" + String.join("` = ?, `", selected.split(", ")) + "` = ?", column, logic), e -> {
                for (int i = 0; i < values.length; i++) {
                    e.setObject(i + 1, values[i]);
                }
                e.setObject(values.length + 1, data);
            }) != -1;
        } else {
            return executeUpdate(getConnection(), "INSERT INTO %s(%s, %s) VALUES(?, %s)".formatted(table, column, selected, String.join(", ", Collections.nCopies(values.length, "?"))), e -> {
                e.setObject(1, data);
                for (int i = 0; i < values.length; i++) {
                    e.setObject(i + 2, values[i]);
                }
            }) != -1;
        }
    }

    @Override
    public boolean remove(String table, String logic, String column, String data) {
        return executeUpdate(getConnection(), "DELETE FROM %s WHERE %s %s ?".formatted(table, column, logic), e -> e.setString(1, data)) != -1;
    }

    @Override
    public Object get(String table, String selected, String logic, String column, String data) {
        return executeQuery(getConnection(), "SELECT %s FROM %s WHERE %s %s ?".formatted(selected, table, column, logic), e -> e.getObject(selected), data);
    }

    @Override
    public <T> T get(String table, String selected, String logic, String column, String data, ClassHandler<T> handler) {
        boolean equals = column == null || column.isEmpty() || logic == null || logic.isEmpty();
        return executeQuery(getConnection(), equals ? "SELECT %s FROM %s WHERE 1".formatted(selected, table) : "SELECT %s FROM %s WHERE %s %s ?".formatted(selected, table, column, logic), handler, equals ? new Object[0] : new Object[]{data});
    }

    @Override
    public List<Object> getList(String table, String selected, String logic, String column, String data) {
        return executeQueryList(getConnection(), "SELECT %s FROM %s WHERE %s %s ?".formatted(selected, table, column, logic), e -> e.getObject(selected), data);
    }

    @Override
    public <T> List<T> getList(String table, String selected, String logic, String column, String data, ClassHandler<T> handler) {
        boolean equals = column == null || column.isEmpty() || logic == null || logic.isEmpty();
        return executeQueryList(getConnection(), equals ? "SELECT %s FROM %s WHERE 1".formatted(selected, table) : "SELECT %s FROM %s WHERE %s %s ?".formatted(selected, table, column, logic), handler, equals ? new Object[0] : new Object[]{data});
    }

    @Override
    public boolean is(String table) {
        try (PreparedStatement prepared = prepared(getConnection(), "SHOW TABLES LIKE ?")) {
            prepared.setString(1, table);
            try (ResultSet res = prepared.executeQuery()) {
                return res.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean is(String table, String column, String data) {
        try (PreparedStatement prepared = prepared(getConnection(), "SELECT EXISTS(SELECT 1 FROM %s WHERE %s = ?)".formatted(table, column))) {
            prepared.setString(1, data);
            try (ResultSet res = prepared.executeQuery()) {
                if (!res.next()) return false;
                return res.getInt(1) == 1;
            }
        } catch (SQLException e) {
            return false;
        }
    }

}