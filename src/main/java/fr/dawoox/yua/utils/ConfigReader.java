package fr.dawoox.yua.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();
    private static FileInputStream fip;

    public static String getToken(){
        try {
            ConfigReader.fip = new FileInputStream("./config.properties");
            prop.load(fip);
        } catch (IOException e) { e.printStackTrace(); }
        return prop.getProperty("token");
    }

}
