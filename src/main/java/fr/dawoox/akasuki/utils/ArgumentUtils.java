package fr.dawoox.akasuki.utils;

import java.util.regex.Pattern;

/**
 * Util class to manage arguments and split them
 * @author Dawoox
 * @version 1.0.0
 */
public class ArgumentUtils {

    /**
     * Get the body of a string and split them
     * @param string
     * The argument
     * @return String
     * Return the body of the argument
     * @since 1.0.0
     */
    public static String[] getBody(String string){
        return Pattern.compile(" ").split(string);
    }

}
