package dev.lone64.mslib.framework.builder.database.impl.connection;

import dev.lone64.mslib.framework.MSLib;
import dev.lone64.mslib.framework.builder.database.data.SQLConnection;
import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteConnection implements SQLConnection {

    private final SQLiteDataSource dataSource;

    public SQLiteConnection(String filePath) {
        this.dataSource = new SQLiteDataSource();
        File pluginFolder = MSLib.getPluginFolder();
        if (!pluginFolder.exists()) pluginFolder.mkdir();
        File dbFile = new File(pluginFolder, filePath);
        this.dataSource.setUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}