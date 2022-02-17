package fr.dawoox.akasuki.data.sqlite;

import discord4j.common.util.Snowflake;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UConfig {

    private static final Logger DB_LOADER_LOGGER = Loggers.getLogger("database-loader");

    public static boolean isAutoModEnabled(Snowflake guild) {
        try {
            String guildId = guild.asString();

            Statement stmt = SQLiteJBC.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, SERVER_ID, \"AUTO-MOD_ENABLED\" FROM UCONFIG WHERE UCONFIG.SERVER_ID = '" + guildId + "'");

            boolean isActivated = Boolean.parseBoolean(rs.getString("AUTO-MOD_ENABLED"));

            rs.close();
            stmt.close();
            return isActivated;
        } catch (SQLException e) {
            DB_LOADER_LOGGER.error("An error occurred", e);
        }

        return false;
    }

}
