package io.github.devlone64.MSLib.builder.database.impl.connection;

import io.github.devlone64.MSLib.MSLib;
import io.github.devlone64.MSLib.builder.database.sql.SQLConnection;
import io.github.devlone64.MSLib.spigot.Spigot;
import lombok.SneakyThrows;

import java.sql.DriverManager;

public class MySQLConnection extends SQLConnection {

    @SneakyThrows
    public MySQLConnection(String host, String port, String name, String username, String password) {
        var logger = MSLib.LOGGER;
        if (isConnection()) return;

        try {
            setConnection(DriverManager.getConnection("jdbc:mysql://%s:%s/%s".formatted(host, port, name), username, password));
        } catch (Exception exception) {
            Spigot.disablePlugin(MSLib.INSTANCE);
            logger.severe("Failed to connect to MySQL server. are the credentials correct?");
            return;
        }

        logger.info("Successfully connected to MySQL.");
    }

}