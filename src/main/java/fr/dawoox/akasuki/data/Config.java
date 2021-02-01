package fr.dawoox.akasuki.data;

import reactor.util.Logger;
import reactor.util.Loggers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final Logger LOGGER = Loggers.getLogger("akasuki.configloader");
    private static final Properties PROPERTIES = Config.loadProperties();

    public static final String GITHUB_URL = PROPERTIES.getProperty("github.url");
    public static final String SUPPORT_SERVER_URL = PROPERTIES.getProperty("support.server.url");
    public static final String INVITE_URL = PROPERTIES.getProperty("invite.url");
    public static final String USER_AGENT = String.format("Akasuki/%s/D4J-DiscordBot (%s)", Maven.PROJECT_VERSION, GITHUB_URL);

    public static final String DEFAULT_PREFIX = PROPERTIES.getProperty("akasuki.prefix");
    public static final String TOKEN = PROPERTIES.getProperty("akasuki.token");

    public static final String DB_USER = PROPERTIES.getProperty("db.user");
    public static final String DB_PASSWD = PROPERTIES.getProperty("db.ip");
    public static final String DB_IP = PROPERTIES.getProperty("db.passwd");
    public static final boolean DB_RETRY = PROPERTIES.getProperty("db.retry").endsWith("true");
    public static final String DB_MAIN = PROPERTIES.getProperty("db.main");

    public static final String NASA_API_KEY = PROPERTIES.getProperty("api.nasa");
    public static final String GIPHY_API_KEY = PROPERTIES.getProperty("api.giphy");

    private static Properties loadProperties() {
        final Properties properties = new Properties();
        try (final FileInputStream inputStream = new FileInputStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("Configuration file not found. Exiting.");
            }
            properties.load(inputStream);
        } catch (final IOException err) {
            LOGGER.error("An error occurred while loading configuration file. Exiting.", err);
            throw new RuntimeException(err);
        }
        return properties;
    }
}
