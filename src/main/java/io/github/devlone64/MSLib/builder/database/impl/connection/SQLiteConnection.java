package io.github.devlone64.MSLib.builder.database.impl.connection;

import io.github.devlone64.MSLib.MSLib;
import io.github.devlone64.MSLib.builder.database.sql.SQLConnection;
import lombok.SneakyThrows;

import java.io.File;
import java.sql.DriverManager;

public class SQLiteConnection extends SQLConnection {

    public SQLiteConnection(String fileName) {
        this(null, fileName);
    }

    @SneakyThrows
    public SQLiteConnection(String dir, String fileName) {
        var logger = MSLib.LOGGER;
        if (isConnection()) return;

        var dataFolder = MSLib.DATA_FOLDER;
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                logger.severe("Cloud not create folder to '%s'.".formatted(dataFolder.getPath().replace("\\", "/")));
            }
        }

        var dirFolder = dataFolder;
        if (dir != null && !dir.isEmpty()) {
            dirFolder = new File(dataFolder, dir);
            if (!dirFolder.exists()) {
                if (!dirFolder.mkdirs()) {
                    logger.severe("Cloud not create folder to '%s'.".formatted(dirFolder.getPath().replace("\\", "/")));
                }
            }
        }

        var dbFile = new File(dirFolder, fileName);
        if (!dbFile.exists()) {
            if (!dbFile.createNewFile()) {
                logger.severe("Cloud not create file to '%s'.".formatted(dbFile.getPath().replace("\\", "/")));
            }
        }

        try {
            Class.forName("org.sqlite.JDBC");
            setConnection(DriverManager.getConnection("jdbc:sqlite:" + dbFile));
        } catch (ClassNotFoundException e) {
            logger.severe("Failed to connect to SQLite server.");
        }
    }

}