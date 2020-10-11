package fr.dawoox.akasuki.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Wrapper for reading the config file
 * @author Dawoox
 * @version 1.0.0
 */
public class ConfigReader {

    private static final Properties prop = new Properties();
    private static FileInputStream fip;

    /**
     * Get a entry into the config file
     * @param id
     * The name of the entry to read from the config
     * @return String
     * The value of the entry
     * @since 1.0.0
     */
    public static String getEntry(String id){
        try {
            ConfigReader.fip = new FileInputStream("./config.properties");
            prop.load(fip);
        } catch (IOException e) { e.printStackTrace(); }
        return prop.getProperty(id);
    }

}
