package fr.dawoox.akasuki.data;

import reactor.util.Logger;
import reactor.util.Loggers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static void main(String[] args){
        System.out.println(VERSION);
    }

    private static final Logger LOGGER = Loggers.getLogger("akasuki.configloader");
    private static final Properties PROPERTIES = ConfigLoader.loadProperties();

    public static final String VERSION = PROPERTIES.getProperty("version");
    public static final boolean IS_SNAPSHOT = VERSION.endsWith("SNAPSHOT");
    public static final String GITHUB_URL = PROPERTIES.getProperty("github.url");
    public static final String SUPPORT_SERVER_URL = PROPERTIES.getProperty("support.server.url");
    public static final String INVITE_URL = PROPERTIES.getProperty("invite.url");
    public static final String USER_AGENT = String.format("Akasuki/%s/D4J-DiscordBot (%s)", VERSION, GITHUB_URL);

    private static Properties loadProperties() {
        final Properties properties = new Properties();
        try (final InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties")) {
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
