package dev.lone64.mslib.framework.builder.database.impl.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.lone64.mslib.framework.builder.database.data.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnection implements SQLConnection {

    private final HikariDataSource dataSource;

    public MySQLConnection(String hostname, String port, String username, String password, String database) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://%s:%s/%s?useUnicode=yes;characterEncoding=utf-8;".formatted(hostname, port, database));
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}