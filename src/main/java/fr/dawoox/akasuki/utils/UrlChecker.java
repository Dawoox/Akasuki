package fr.dawoox.akasuki.utils;

import java.net.URL;

/**
 * Util class for validate a url.
 * @author Dawoox
 * @version 1.0.0
 */
public class UrlChecker {

    /**
     * Check if a Url is valid
     * @param url
     * A url into a string
     * @return
     * Returns whether or not the url is valid
     * @since 1.0.0
     */
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
