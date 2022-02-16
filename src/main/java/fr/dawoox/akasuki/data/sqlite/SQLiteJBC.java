package fr.dawoox.akasuki.data.sqlite;

import fr.dawoox.akasuki.data.Config;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteJBC {
    private static final Logger DB_LOGGER = Loggers.getLogger("database");

    private static Connection connection = null;

    public static void initialize() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+ Config.DATABASE_NAME);
            DB_LOGGER.info("Opened database successfully");

            Statement stmt = connection.createStatement();

            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static 

}
