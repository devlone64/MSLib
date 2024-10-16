package io.github.devlone64.MSLib.builder.database.impl.connection;

import io.github.devlone64.MSLib.MSPlugin;
import io.github.devlone64.MSLib.builder.database.data.SQLConnection;
import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteConnection implements SQLConnection {

    private final SQLiteDataSource dataSource;

    public SQLiteConnection(String filePath) {
        this.dataSource = new SQLiteDataSource();
        File pluginFolder = MSPlugin.INSTANCE.getDataFolder();
        if (!pluginFolder.exists()) pluginFolder.mkdir();
        File dbFile = new File(pluginFolder, filePath);
        this.dataSource.setUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}