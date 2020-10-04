package fr.dawoox.akasuki.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();
    private static FileInputStream fip;

    public static String getEntry(String id){
        try {
            ConfigReader.fip = new FileInputStream("./config.properties");
            prop.load(fip);
        } catch (IOException e) { e.printStackTrace(); }
        return prop.getProperty(id);
    }

}
