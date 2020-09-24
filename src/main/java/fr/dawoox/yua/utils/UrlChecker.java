package fr.dawoox.yua.utils;

import java.net.URL;

public class UrlChecker {

    public static boolean isValid(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }

}
