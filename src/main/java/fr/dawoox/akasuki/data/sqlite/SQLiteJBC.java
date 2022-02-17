package fr.dawoox.akasuki.data.sqlite;

import fr.dawoox.akasuki.data.Config;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteJBC {
    private static final Logger DB_LOGGER = Loggers.getLogger("database");

    private static Connection connection = null;

    public static void initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ Config.DATABASE_NAME);
            connection.setAutoCommit(false);
            DB_LOGGER.info("Opened database successfully");
        } catch (Exception e) {
            DB_LOGGER.error("An error occurred", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
